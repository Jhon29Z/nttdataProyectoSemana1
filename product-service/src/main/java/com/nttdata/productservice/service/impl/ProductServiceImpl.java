package com.nttdata.productservice.service.impl;

import com.nttdata.productservice.document.Product;
import com.nttdata.productservice.document.ProductType;
import com.nttdata.productservice.repository.ProductRepository;
import com.nttdata.productservice.repository.ProductTypeRepository;
import com.nttdata.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductTypeRepository productTypeRepository;

    @Override
    public Flux<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Mono<Product> save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Mono<Product> findById(String id) {
        return productRepository.findById(id);
    }

    @Override
    public Mono<Boolean> existsById(String id) {
        return productRepository.existsById(id);
    }

    @Override
    public Mono<Void> delete(String id) {
        return productRepository.deleteById(id);
    }
}
