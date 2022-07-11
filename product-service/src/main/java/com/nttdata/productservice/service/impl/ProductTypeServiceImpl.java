package com.nttdata.productservice.service.impl;

import com.nttdata.productservice.document.ProductType;
import com.nttdata.productservice.repository.ProductTypeRepository;
import com.nttdata.productservice.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {
    @Autowired
    ProductTypeRepository productTypeRepository;
    @Override
    public Flux<ProductType> findAll() {
        return productTypeRepository.findAll();
    }

    @Override
    public Mono<ProductType> save(ProductType productType) {
        return productTypeRepository.save(productType);
    }

    @Override
    public Mono<ProductType> findById(String id) {
        return productTypeRepository.findById(id);
    }

    @Override
    public Mono<Void> delete(String id) {
        return productTypeRepository.deleteById(id);
    }
}
