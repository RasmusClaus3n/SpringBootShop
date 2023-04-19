package com.rasmusclausen.shopproject.service;

import com.rasmusclausen.shopproject.entity.Product;
import com.rasmusclausen.shopproject.exception.ProductNotFoundException;
import com.rasmusclausen.shopproject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    // The methods bellow are used for fetching the products from the database
    // taking into account the different user search/filter parameters

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

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
}


