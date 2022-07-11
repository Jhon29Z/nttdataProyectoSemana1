package com.nttdata.transactionservice.repository;

import com.nttdata.transactionservice.document.TransactionType;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionTypeRepository extends ReactiveMongoRepository<TransactionType,String> {
}
