package com.nttdata.transactionservice.repository;

import com.nttdata.transactionservice.document.Transaction;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TransactionRepository extends ReactiveMongoRepository<Transaction,String> {
}
