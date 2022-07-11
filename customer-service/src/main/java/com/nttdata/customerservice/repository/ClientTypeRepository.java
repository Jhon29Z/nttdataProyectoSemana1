package com.nttdata.customerservice.repository;

import com.nttdata.customerservice.document.ClientType;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientTypeRepository extends ReactiveMongoRepository<ClientType,String> {
}
