package com.nttdata.accountservice.service;

import com.nttdata.accountservice.document.Currency;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CurrencyService {
    Flux<Currency> findAll();
    Mono<Currency> save(Currency currency);
    Mono<Currency> findById(String id);
    Mono<Void> delete(String id);
}
