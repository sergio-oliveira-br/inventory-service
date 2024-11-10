package com.alucontrol.inventoryservice.services.business;

import com.alucontrol.inventoryservice.entity.Product;
import com.alucontrol.inventoryservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReadProductServices {

    private final ProductRepository productRepository;

    @Autowired
    public ReadProductServices(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow();
    }
}
