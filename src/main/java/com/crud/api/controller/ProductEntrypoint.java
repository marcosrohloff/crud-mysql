package com.crud.api.controller;

import com.crud.api.domain.Product;
import com.crud.api.exception.resource.ResourceNotFoundException;
import com.crud.api.http.ProductHttp;
import com.crud.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductEntrypoint {

    private ProductService productService;

    @Autowired
    public ProductEntrypoint(final ProductService productService){
        this.productService = productService;
    }

    @GetMapping()
    public ResponseEntity<Page<Product>> listAll() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductHttp> listAll(@PathVariable("id") Long id) throws ResourceNotFoundException {
        ProductHttp product = productService.findOne(id);
        if (product == null) {
            throw new ResourceNotFoundException("Product not found in database!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(productService.findOne(id));
    }

    @PostMapping()
    public ResponseEntity<ProductHttp> create(@RequestBody ProductHttp productHttp) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(productHttp));
        } catch (Exception e) {
            throw new Exception("Problems on save data! Error: " + e.getMessage() );
        }
    }

    @PutMapping()
    public ResponseEntity<ProductHttp> update(@RequestBody ProductHttp productHttp) throws Exception {
        ProductHttp product = productService.update(productHttp);
        if (product != null) {
            return ResponseEntity.status(HttpStatus.OK).body(product);
        } else {
            throw new Exception("Problems on update data!");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        productService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
 }
