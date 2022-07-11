package com.nttdata.accountservice.service.impl;

import com.nttdata.accountservice.document.AuthorizedSigner;
import com.nttdata.accountservice.repository.AuthorizedSignerRepository;
import com.nttdata.accountservice.service.AuthorizedSignerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AuthorizedSignerServiceImpl implements AuthorizedSignerService {

    @Autowired
    AuthorizedSignerRepository authorizedSignerRepository;

    @Override
    public Flux<AuthorizedSigner> findAll() {
        return authorizedSignerRepository.findAll();
    }

    @Override
    public Mono<AuthorizedSigner> save(AuthorizedSigner authorizedSigner) {
        return authorizedSignerRepository.save(authorizedSigner);
    }

    @Override
    public Mono<AuthorizedSigner> findById(String id) {
        return authorizedSignerRepository.findById(id);
    }


    @Override
    public Mono<Void> delete(String id) {
        return authorizedSignerRepository.deleteById(id);
    }
}
