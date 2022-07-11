package com.nttdata.customerservice.service.impl;

import com.nttdata.customerservice.document.Client;
import com.nttdata.customerservice.document.ClientType;
import com.nttdata.customerservice.repository.ClientRepository;
import com.nttdata.customerservice.repository.ClientTypeRepository;
import com.nttdata.customerservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.Unwrapped;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    ClientTypeRepository clientTypeRepository;

    @Override
    public Flux<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Mono<Client> save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Mono<Client> findById(String id) {
        return clientRepository.findById(id);
    }

    @Override
    public Mono<Void> delete(String id) {
        return clientRepository.deleteById(id);
    }


}
