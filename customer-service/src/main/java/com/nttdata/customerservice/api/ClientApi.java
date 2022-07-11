package com.nttdata.customerservice.api;

import com.nttdata.customerservice.document.Client;
import com.nttdata.customerservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/clients")
public class ClientApi {
    @Autowired
    ClientService clientService;

    @GetMapping
    Mono<ResponseEntity<Flux<Client>>> findAll(){
        return Mono.just(
                ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .body(clientService.findAll())
        );
    }

    @GetMapping("/{id}")
    Mono<ResponseEntity<Client>> findById(@PathVariable("id") String id){
        return clientService.findById(id).map(c-> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .body(c))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    Mono<ResponseEntity<Map<String,Object>>> save(@Valid @RequestBody Mono<Client> client){
        Map<String,Object> response = new HashMap<>();

        return client.flatMap(pt-> clientService.save(pt).map(pt2-> {
            response.put("Client ", pt2);
            response.put("message", "successfully saved");
            response.put("timestamp", new Date());
            return ResponseEntity
                    .created(URI.create(("/clients/".concat(pt2.getId()))))
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .body(response);
        })).onErrorResume(e->{
            return Mono.just(e).cast(WebExchangeBindException.class)
                    .flatMap(e2-> Mono.just(e2.getFieldErrors()))
                    .flatMapMany(Flux::fromIterable)
                    .map(fieldError ->"The field : "+ fieldError.getField() + "" + fieldError.getDefaultMessage())
                    .collectList()
                    .flatMap(list->{
                        response.put("errors", list);
                        response.put("timestamp", new Date());
                        response.put("status", HttpStatus.BAD_REQUEST.value());

                        return Mono.just(ResponseEntity.badRequest().body(response));
                    });
        });
    }

    @PutMapping("/{id}")
    Mono<ResponseEntity<Client>> update(@RequestBody Client client, @PathVariable String id){
        return clientService.findById(id).flatMap(pt -> {
                    pt.setCustomerTypeId(client.getCustomerTypeId());
                    pt.setName(client.getName());
                    pt.setSurname(client.getSurname());
                    pt.setBusinessName(client.getBusinessName());
                    pt.setPhone(client.getPhone());
                    pt.setAddress(client.getAddress());
                    pt.setEmail(client.getEmail());
                    pt.setDocumentTypeId(client.getDocumentTypeId());
                    pt.setNumberDocumentType(client.getNumberDocumentType());
                    pt.setDateOfBirth(client.getDateOfBirth());
                    return clientService.save(pt);
                }).map(pt-> ResponseEntity.created(URI.create("/clients/".concat(pt.getId())))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .body(pt))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    Mono<ResponseEntity<Void>> delete(@PathVariable("id") String id){
        return clientService.findById(id).flatMap(pt->{
            return clientService.delete(id).then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
        }).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
    }

}
