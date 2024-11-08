package com.alucontrol.inventoryservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data  //Generates all methods getter, setter, equals, hashCode, toString and the constructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private ProductType type; //From Enum

    //@NonNull(message = "O nome do produto é essencial para identificação")
    private String name;
    private String description;

    //@Positive(message = "Preço obrigatoriamente deve ser positivo")
    private BigDecimal price;

    //@Positive(message = "Esta quantidade refere-se a sua quantidade disponivel em estoque, e deve ser positiva")
    private Long qtyAvailable;
    private Long qtySold;
    private Long qtyRented;
    private Long qtyRentedInProgress;
    private LocalDateTime lastMove;

    @CreationTimestamp
    private LocalDateTime createdAt;

}

