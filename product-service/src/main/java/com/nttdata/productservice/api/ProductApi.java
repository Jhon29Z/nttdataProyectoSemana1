package com.nttdata.productservice.api;

import com.nttdata.productservice.document.Product;
import com.nttdata.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductApi {
    @Autowired
    ProductService productService;

    @GetMapping
    Mono<ResponseEntity<Flux<Product>>> findAll(){
        return Mono.just(
                ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .body(productService.findAll())
        );
    }

    @GetMapping("/{id}")
    Mono<ResponseEntity<Product>> findById(@PathVariable("id") String id){
        return productService.findById(id).map(c-> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .body(c))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    Mono<ResponseEntity<Map<String,Object>>> save(@Valid @RequestBody Mono<Product> product){
        Map<String,Object> response = new HashMap<>();

        return product.flatMap(pt-> productService.save(pt).map(pt2-> {
            response.put("Product", pt2);
            response.put("message", "successfully saved");
            response.put("timestamp", new Date());
            return ResponseEntity
                    .created(URI.create(("/products/".concat(pt2.getId()))))
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .body(response);
        })).onErrorResume(e->{
            return Mono.just(e).cast(WebExchangeBindException.class)
                    .flatMap(e2-> Mono.just(e2.getFieldErrors()))
                    .flatMapMany(Flux::fromIterable)
                    .map(fieldError ->"The field : "+ fieldError.getField() + "" + fieldError.getDefaultMessage())
                    .collectList()
                    .flatMap(list->{
                        response.put("errors", list);
                        response.put("timestamp", new Date());
                        response.put("status", HttpStatus.BAD_REQUEST.value());

                        return Mono.just(ResponseEntity.badRequest().body(response));
                    });
        });
    }

    @PutMapping("/{id}")
    Mono<ResponseEntity<Product>> update(@RequestBody Product product, @PathVariable String id){
        return productService.findById(id).flatMap(pt -> {
                    pt.setCategory(product.getCategory());
                    pt.setProductTypeId(product.getProductTypeId());
                    return productService.save(pt);
                }).map(pt-> ResponseEntity.created(URI.create("/products/".concat(pt.getId())))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .body(pt))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    Mono<ResponseEntity<Void>> delete(@PathVariable("id") String id){
        return productService.findById(id).flatMap(pt->{
            return productService.delete(id).then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
        }).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
    }

}
