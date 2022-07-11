package com.nttdata.accountservice.service.impl;

import com.nttdata.accountservice.document.Currency;
import com.nttdata.accountservice.repository.CurrencyRepository;
import com.nttdata.accountservice.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    CurrencyRepository currencyRepository;


    @Override
    public Flux<Currency> findAll() {
        return currencyRepository.findAll();
    }

    @Override
    public Mono<Currency> save(Currency currency) {
        return currencyRepository.save(currency);
    }

    @Override
    public Mono<Currency> findById(String id) {
        return currencyRepository.findById(id);
    }

    @Override
    public Mono<Void> delete(String id) {
        return currencyRepository.deleteById(id);
    }
}
