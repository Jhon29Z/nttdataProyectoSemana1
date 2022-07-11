package com.nttdata.accountservice.repository;

import com.nttdata.accountservice.document.AuthorizedSigner;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorizedSignerRepository extends ReactiveMongoRepository<AuthorizedSigner,String> {
}
