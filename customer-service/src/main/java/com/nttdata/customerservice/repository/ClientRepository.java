package com.nttdata.customerservice.repository;

import com.nttdata.customerservice.document.Client;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ClientRepository extends ReactiveMongoRepository<Client,String> {

}
