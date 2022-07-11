package com.nttdata.accountservice.service;

import com.nttdata.accountservice.document.Holder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface HolderService {
    Flux<Holder> findAll();
    Mono<Holder> save(Holder holder);
    Mono<Holder> findById(String id);
    Mono<Void> delete(String id);
}
