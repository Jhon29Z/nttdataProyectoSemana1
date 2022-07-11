package com.nttdata.transactionservice.service;

import com.nttdata.transactionservice.document.TransactionType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionTypeService {
    Flux<TransactionType> findAll();
    Mono<TransactionType> save(TransactionType transactionType);
    Mono<TransactionType> findById(String id);
    Mono<Void> delete(String id);
}
