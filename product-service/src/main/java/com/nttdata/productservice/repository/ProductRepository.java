package com.nttdata.productservice.repository;

import com.nttdata.productservice.document.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product,String> {
}
