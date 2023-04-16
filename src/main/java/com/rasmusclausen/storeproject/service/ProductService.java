package com.rasmusclausen.storeproject.service;

import com.rasmusclausen.storeproject.entity.Product;
import com.rasmusclausen.storeproject.exception.ProductNotFoundException;
import com.rasmusclausen.storeproject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Product findProductById(Long id){
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException
                        ("Product with id " + id + " was not found"));
    }

    public Page<Product> findByPlatformAndName(String platform, String name, Pageable pageable) {
        return productRepository.findByPlatformContainingIgnoreCaseAndNameContainingIgnoreCase
                (platform, name, pageable);
    }

    public Page<Product> findByPlatform(String platform, Pageable pageable) {
        return productRepository.findByPlatformContainingIgnoreCase
                (platform, pageable);
    }

    public Page<Product> findByName(String name, Pageable pageable) {
        return productRepository.findByNameContainingIgnoreCase
                (name, pageable);
    }

    public Page<Product> findAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public long getProductCountByPlatform(String platform) {
        return productRepository.getCountByPlatform(platform);
    }
}


