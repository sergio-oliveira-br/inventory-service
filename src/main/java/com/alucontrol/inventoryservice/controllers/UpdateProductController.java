package com.alucontrol.inventoryservice.controllers;

import com.alucontrol.inventoryservice.entity.Product;
import com.alucontrol.inventoryservice.services.business.UpdateProductService;
import com.alucontrol.inventoryservice.tracking.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/products")
public class UpdateProductController {

    private final UpdateProductService updateProductService;

    @Autowired
    public UpdateProductController(UpdateProductService updateProductService) {
        this.updateProductService = updateProductService;
    }

    @PutMapping("/decrease-stock/product-id/{productId}")
    public ResponseEntity<Void> updateStock(@PathVariable Long productId,
                                               @RequestParam int requestedQuantity) {

        updateProductService.decreaseStock(productId, requestedQuantity);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/increase-sold/product-id/{productId}")
    public ResponseEntity<Void> updateSold(@PathVariable Long productId,
                                           @RequestParam int requestedQuantity) {

        updateProductService.increaseSoldQty(productId, requestedQuantity);
        return ResponseEntity.ok().build();
    }
}
