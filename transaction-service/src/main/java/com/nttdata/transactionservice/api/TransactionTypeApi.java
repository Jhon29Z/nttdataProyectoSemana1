package com.nttdata.transactionservice.api;

import com.nttdata.transactionservice.document.TransactionType;
import com.nttdata.transactionservice.service.TransactionTypeService;
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
@RequestMapping("/transactionTypes")
public class TransactionTypeApi {
    @Autowired
    TransactionTypeService transactionTypeService;

    @GetMapping
    Mono<ResponseEntity<Flux<TransactionType>>> findAll(){
        return Mono.just(
                ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .body(transactionTypeService.findAll())
        );
    }

    @GetMapping("/{id}")
    Mono<ResponseEntity<TransactionType>> findById(@PathVariable("id") String id){
        return transactionTypeService.findById(id).map(c-> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .body(c))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    Mono<ResponseEntity<Map<String,Object>>> save(@Valid @RequestBody Mono<TransactionType> transactionTypeMono){
        Map<String,Object> response = new HashMap<>();

        return transactionTypeMono.flatMap(pt-> transactionTypeService.save(pt).map(pt2-> {
            response.put("Transaction Type ", pt2);
            response.put("message ", "successfully saved");
            response.put("timestamp", new Date());
            return ResponseEntity
                    .created(URI.create(("/transactionTypes/".concat(pt2.getId()))))
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
    Mono<ResponseEntity<TransactionType>> update(@RequestBody TransactionType transactionType, @PathVariable String id){
        return transactionTypeService.findById(id).flatMap(pt -> {
                    pt.setDescription(transactionType.getDescription());
                    return transactionTypeService.save(pt);
                }).map(pt-> ResponseEntity.created(URI.create("/transactionTypes/".concat(pt.getId())))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .body(pt))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    Mono<ResponseEntity<Void>> delete(@PathVariable("id") String id){
        return transactionTypeService.findById(id).flatMap(pt->{
            return transactionTypeService.delete(id).then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
        }).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
    }
}

