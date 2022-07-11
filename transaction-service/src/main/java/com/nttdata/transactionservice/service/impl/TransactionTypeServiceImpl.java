package com.nttdata.transactionservice.service.impl;

import com.nttdata.transactionservice.document.TransactionType;
import com.nttdata.transactionservice.repository.TransactionTypeRepository;
import com.nttdata.transactionservice.service.TransactionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TransactionTypeServiceImpl implements TransactionTypeService {

    @Autowired
    TransactionTypeRepository transactionTypeRepository;

    @Override
    public Flux<TransactionType> findAll() {
        return transactionTypeRepository.findAll();
    }

    @Override
    public Mono<TransactionType> save(TransactionType transactionType) {
        return transactionTypeRepository.save(transactionType);
    }

    @Override
    public Mono<TransactionType> findById(String id) {
        return transactionTypeRepository.findById(id);
    }

    @Override
    public Mono<Void> delete(String id) {
        return transactionTypeRepository.deleteById(id);
    }
}
