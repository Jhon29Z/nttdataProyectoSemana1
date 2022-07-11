package com.nttdata.accountservice.api;

import com.nttdata.accountservice.document.Holder;
import com.nttdata.accountservice.service.HolderService;
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
@RequestMapping("/holders")
public class HolderApi {

    @Autowired
    HolderService holderService;

    @GetMapping
    Mono<ResponseEntity<Flux<Holder>>> findAll(){
        return Mono.just(
                ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .body(holderService.findAll())
        );
    }

    @GetMapping("/{id}")
    Mono<ResponseEntity<Holder>> findById(@PathVariable("id") String id){
        return holderService.findById(id).map(c-> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .body(c))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    Mono<ResponseEntity<Map<String,Object>>> save(@Valid @RequestBody Mono<Holder> holderMono){
        Map<String,Object> response = new HashMap<>();

        return holderMono.flatMap(pt-> holderService.save(pt).map(pt2-> {
            response.put("Holder ", pt2);
            response.put("message", "successfully saved");
            response.put("timestamp", new Date());
            return ResponseEntity
                    .created(URI.create(("/holders/".concat(pt2.getId()))))
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
    Mono<ResponseEntity<Holder>> update(@RequestBody Holder holder, @PathVariable String id){
        return holderService.findById(id).flatMap(pt -> {
                    pt.setAccountId(holder.getAccountId());
                    pt.setName(holder.getName());
                    pt.setSurname(holder.getSurname());
                    pt.setPhone(holder.getPhone());
                    pt.setAddress(holder.getAddress());
                    pt.setEmail(holder.getEmail());
                    pt.setDateOfBirth(holder.getDateOfBirth());
                    return holderService.save(pt);
                }).map(pt-> ResponseEntity.created(URI.create("/holders/".concat(pt.getId())))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .body(pt))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    Mono<ResponseEntity<Void>> delete(@PathVariable("id") String id){
        return holderService.findById(id).flatMap(pt->{
            return holderService.delete(id).then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
        }).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
    }
}
