package com.nttdata.transactionservice.service.impl;

import com.nttdata.transactionservice.document.Transaction;
import com.nttdata.transactionservice.repository.TransactionRepository;
import com.nttdata.transactionservice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public Flux<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Mono<Transaction> save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public Mono<Transaction> findById(String id) {
        return transactionRepository.findById(id);
    }

    @Override
    public Mono<Void> delete(String id) {
        return transactionRepository.deleteById(id);
    }
}
