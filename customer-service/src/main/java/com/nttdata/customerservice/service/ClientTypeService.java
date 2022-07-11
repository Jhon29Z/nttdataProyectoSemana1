package com.nttdata.customerservice.service;

import com.nttdata.customerservice.document.ClientType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientTypeService {
    Flux<ClientType> findAll();
    Mono<ClientType> save(ClientType clientType);
    Mono<ClientType> findById(String id);
    Mono<Void> delete(String id);
}
