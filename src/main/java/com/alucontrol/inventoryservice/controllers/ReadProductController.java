package com.alucontrol.inventoryservice.controllers;

import com.alucontrol.inventoryservice.entity.Product;
import com.alucontrol.inventoryservice.services.business.ReadProductServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ReadProductController {

    private final ReadProductServices readProductServices;

    @Autowired
    public ReadProductController(ReadProductServices readProductServices) {
        this.readProductServices = readProductServices;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {

        List<Product> allProductsFound = readProductServices.findAll();
        return ResponseEntity.ok(allProductsFound);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@Valid @RequestParam Long id) {

        Product productFound = readProductServices.findById(id);
        return ResponseEntity.ok(productFound);
    }
}
