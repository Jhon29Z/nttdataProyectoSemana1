package com.nttdata.accountservice.service.impl;

import com.nttdata.accountservice.document.Holder;
import com.nttdata.accountservice.repository.HolderRepository;
import com.nttdata.accountservice.service.HolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class HolderServiceImpl implements HolderService {
    @Autowired
    HolderRepository holderRepository;

    @Override
    public Flux<Holder> findAll() {
        return holderRepository.findAll();
    }

    @Override
    public Mono<Holder> save(Holder holder) {
        return holderRepository.save(holder);
    }

    @Override
    public Mono<Holder> findById(String id) {
        return holderRepository.findById(id);
    }

    @Override
    public Mono<Void> delete(String id) {
        return holderRepository.deleteById(id);
    }
}
