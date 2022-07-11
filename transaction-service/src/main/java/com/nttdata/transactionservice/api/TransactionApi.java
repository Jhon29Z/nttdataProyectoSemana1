package com.nttdata.transactionservice.api;

import com.nttdata.transactionservice.document.Transaction;
import com.nttdata.transactionservice.document.TransactionType;
import com.nttdata.transactionservice.service.TransactionService;
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
@RequestMapping("/transactions")
public class TransactionApi {
    @Autowired
    TransactionService transactionService;

    @GetMapping
    Mono<ResponseEntity<Flux<Transaction>>> findAll(){
        return Mono.just(
                ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .body(transactionService.findAll())
        );
    }

    @GetMapping("/{id}")
    Mono<ResponseEntity<Transaction>> findById(@PathVariable("id") String id){
        return transactionService.findById(id).map(c-> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .body(c))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    Mono<ResponseEntity<Map<String,Object>>> save(@Valid @RequestBody Mono<Transaction> transactionMono){
        Map<String,Object> response = new HashMap<>();

        return transactionMono.flatMap(pt-> transactionService.save(pt).map(pt2-> {
            response.put("Transaction ", pt2);
            response.put("message ", "successfully saved");
            response.put("timestamp", new Date());
            return ResponseEntity
                    .created(URI.create(("/transactions/".concat(pt2.getId()))))
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
    Mono<ResponseEntity<Transaction>> update(@RequestBody Transaction transaction, @PathVariable String id){
        return transactionService.findById(id).flatMap(pt -> {
                    pt.setAccountId(transaction.getAccountId());
                    pt.setAmount(transaction.getAmount());
                    pt.setTransactionTypeId(transaction.getTransactionTypeId());
                    pt.setCreationDate(transaction.getCreationDate());
                    return transactionService.save(pt);
                }).map(pt-> ResponseEntity.created(URI.create("/transactions/".concat(pt.getId())))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .body(pt))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    Mono<ResponseEntity<Void>> delete(@PathVariable("id") String id){
        return transactionService.findById(id).flatMap(pt->{
            return transactionService.delete(id).then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
        }).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
    }
}
