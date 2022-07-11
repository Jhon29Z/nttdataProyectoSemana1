package com.nttdata.transactionservice.service;
;
import com.nttdata.transactionservice.document.Transaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionService {
    Flux<Transaction> findAll();
    Mono<Transaction> save(Transaction transaction);
    Mono<Transaction> findById(String id);
    Mono<Void> delete(String id);
}
