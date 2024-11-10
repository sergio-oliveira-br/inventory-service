package com.alucontrol.inventoryservice.controllers;


import com.alucontrol.inventoryservice.entity.Product;
import com.alucontrol.inventoryservice.services.business.CreateProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class CreateProductController {

    private final CreateProductService createProductService;

    @Autowired
    public CreateProductController(CreateProductService createProductService) {
        this.createProductService = createProductService;
    }


    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createProductService.saveProduct(product));
    }


}
