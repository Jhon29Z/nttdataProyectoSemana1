package com.nttdata.productservice.repository;

import com.nttdata.productservice.document.ProductType;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ProductTypeRepository extends ReactiveMongoRepository<ProductType,String> {

}
