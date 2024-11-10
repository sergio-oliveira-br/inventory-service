package com.alucontrol.inventoryservice.repository;

import com.alucontrol.inventoryservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
