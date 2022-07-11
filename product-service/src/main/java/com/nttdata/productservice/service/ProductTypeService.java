package com.nttdata.productservice.service;

import com.nttdata.productservice.document.ProductType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductTypeService {
    Flux<ProductType> findAll();
    Mono<ProductType> save(ProductType productType);
    Mono<ProductType> findById(String id);
    Mono<Void> delete(String id);
}
