package com.nttdata.customerservice.service;

import com.nttdata.customerservice.document.Client;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientService {
    Flux<Client> findAll();
    Mono<Client> save(Client client);
    Mono<Client> findById(String id);
    Mono<Void> delete(String id);

}
