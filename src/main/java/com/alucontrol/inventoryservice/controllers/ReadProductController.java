package com.alucontrol.inventoryservice.controllers;

import com.alucontrol.inventoryservice.entity.Product;
import com.alucontrol.inventoryservice.services.business.ReadProductServices;
import com.alucontrol.inventoryservice.tracking.LogUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Product> getProductById(@Valid @PathVariable Long id) {

        Product productFound = readProductServices.findById(id);
        return ResponseEntity.ok(productFound);
    }

    @GetMapping("/check-inventory/product-id/{productId}")
    public ResponseEntity<Boolean> checkInventory(@PathVariable("productId") Long productId,
                                                  @RequestParam("requestedQuantity") int requestedQuantity) {

        boolean hasStock = readProductServices.hasSufficientStock(productId, requestedQuantity);
        LogUtil.info("Resposta do Metodo CheckInventory dentro do Inventory-Service. HasStock: " + hasStock);
        return ResponseEntity.ok(hasStock);
    }
}
