package com.alucontrol.inventoryservice.services.business;

import com.alucontrol.inventoryservice.entity.Product;
import com.alucontrol.inventoryservice.exceptions.ProductNotFoundException;
import com.alucontrol.inventoryservice.repository.ProductRepository;
import com.alucontrol.inventoryservice.tracking.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UpdateProductService {

    private final ProductRepository productRepository;

    @Autowired
    public UpdateProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // This will decrese the stock according the sales, and this annotation,
    // helps to ensure data integrity in transactional systems
    @Transactional
    public void decreaseStock(Long productId, int requestedQuantity) {

        //check if the product exist into DB
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            product.setQtyAvailable(product.getQtyAvailable() - requestedQuantity);

            LogUtil.logDatabaseOperation("Produto id '" + productId +
                    "' teve o estoque reduzido em '" + requestedQuantity+ "' un");

            productRepository.save(product);
            return;
        }
        throw new ProductNotFoundException("Produto id '" + productId + "' não encontrado");
    }


    @Transactional
    public void increaseSoldQty(Long productId, int requestedQuantity) {

        // Check if the product exist in the DB
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            product.setQtySold(product.getQtySold() + requestedQuantity);
            LogUtil.logDatabaseOperation("Quantidade '" + requestedQuantity + "' incrementada nas vendas do produto id '" + productId + "'");
            productRepository.save(product);
            return;
        }
        throw new ProductNotFoundException("Produto id '" + productId + "' não encontrado");
    }
}
