package com.crud.api.service;

import com.crud.api.domain.Product;
import com.crud.api.http.ProductHttp;
import com.crud.api.http.ProductMapper;
import com.crud.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> findAll() {
        Pageable pageable = PageRequest.of(0, 10);
        return productRepository.findAll(pageable);
    }

    public ProductHttp update(ProductHttp productHttp) {
        Optional<Product> productSearch = productRepository.findById(productHttp.getId());
        return productSearch.isPresent() ? ProductMapper
                .tableToHttp(productRepository.save(ProductMapper.httpToTable(productHttp))) : null;
    }

    public ProductHttp findOne(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(ProductMapper::tableToHttp).orElse(null);
    }

    public ProductHttp save(ProductHttp productHttp) {
        return ProductMapper.tableToHttp(productRepository.save(ProductMapper.httpToTable(productHttp)));
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
