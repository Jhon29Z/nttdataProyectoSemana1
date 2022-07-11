package com.nttdata.accountservice.service;

import com.nttdata.accountservice.document.Account;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountService {
    Flux<Account> findAll();
    Mono<Account> save(Account account);
    Mono<Account> findById(String id);
    Mono<Void> delete(String id);
}
