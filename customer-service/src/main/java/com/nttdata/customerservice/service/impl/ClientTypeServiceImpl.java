package com.nttdata.customerservice.service.impl;

import com.nttdata.customerservice.document.ClientType;
import com.nttdata.customerservice.repository.ClientTypeRepository;
import com.nttdata.customerservice.service.ClientTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientTypeServiceImpl implements ClientTypeService {
    @Autowired
    ClientTypeRepository clientTypeRepository;

    @Override
    public Flux<ClientType> findAll() {
        return clientTypeRepository.findAll();
    }

    @Override
    public Mono<ClientType> save(ClientType clientType) {
        return clientTypeRepository.save(clientType);
    }

    @Override
    public Mono<ClientType> findById(String id) {
        return clientTypeRepository.findById(id);
    }

    @Override
    public Mono<Void> delete(String id) {
        return clientTypeRepository.deleteById(id);
    }

}
