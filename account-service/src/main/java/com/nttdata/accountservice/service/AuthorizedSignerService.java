package com.nttdata.accountservice.service;

import com.nttdata.accountservice.document.AuthorizedSigner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AuthorizedSignerService {

    Flux<AuthorizedSigner> findAll();
    Mono<AuthorizedSigner> save(AuthorizedSigner authorizedSigner);
    Mono<AuthorizedSigner> findById(String id);
    Mono<Void> delete(String id);
}
