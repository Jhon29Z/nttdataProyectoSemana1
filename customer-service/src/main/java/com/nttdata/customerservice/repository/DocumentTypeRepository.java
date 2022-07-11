package com.nttdata.customerservice.repository;

import com.nttdata.customerservice.document.DocumentType;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentTypeRepository extends ReactiveMongoRepository<DocumentType, String> {
}
