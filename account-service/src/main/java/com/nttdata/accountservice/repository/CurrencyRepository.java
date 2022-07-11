package com.nttdata.accountservice.repository;

import com.nttdata.accountservice.document.Currency;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends ReactiveMongoRepository<Currency,String> {
}
