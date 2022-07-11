package com.nttdata.productservice.service;

import com.nttdata.productservice.document.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {
    Flux<Product> findAll();
    Mono<Product> save(Product product);
    Mono<Product> findById(String id);
    Mono<Boolean> existsById (String id);
    Mono<Void> delete(String id);
}
