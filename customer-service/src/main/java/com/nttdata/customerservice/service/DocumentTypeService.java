package com.nttdata.customerservice.service;

import com.nttdata.customerservice.document.DocumentType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DocumentTypeService {

    Flux<DocumentType> findAll();
    Mono<DocumentType> save(DocumentType documentType);
    Mono<DocumentType> findById(String id);
    Mono<Void> delete(String id);
}
