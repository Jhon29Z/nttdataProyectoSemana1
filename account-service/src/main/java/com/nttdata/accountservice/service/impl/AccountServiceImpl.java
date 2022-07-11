package com.nttdata.accountservice.service.impl;

import com.nttdata.accountservice.document.Account;
import com.nttdata.accountservice.repository.AccountRepository;
import com.nttdata.accountservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    RestTemplate restTemplate;

    @Override
    public Flux<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Mono<Account> save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Mono<Account> findById(String id) {
        return accountRepository.findById(id);
    }


    @Override
    public Mono<Void> delete(String id) {
        return accountRepository.deleteById(id);
    }

}
