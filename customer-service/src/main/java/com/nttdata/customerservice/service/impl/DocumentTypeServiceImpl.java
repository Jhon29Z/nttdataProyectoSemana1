package com.nttdata.customerservice.service.impl;

import com.nttdata.customerservice.document.DocumentType;
import com.nttdata.customerservice.repository.DocumentTypeRepository;
import com.nttdata.customerservice.service.DocumentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DocumentTypeServiceImpl implements DocumentTypeService {
    @Autowired
    DocumentTypeRepository documentTypeRepository;

    @Override
    public Flux<DocumentType> findAll() {
        return documentTypeRepository.findAll();
    }

    @Override
    public Mono<DocumentType> save(DocumentType documentType) {
        return documentTypeRepository.save(documentType);
    }

    @Override
    public Mono<DocumentType> findById(String id) {
        return documentTypeRepository.findById(id);
    }

    @Override
    public Mono<Void> delete(String id) {
        return documentTypeRepository.deleteById(id);
    }
}
