package com.alucontrol.inventoryservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data  //Generates all methods getter, setter, equals, hashCode, toString and the constructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private ProductType type; //From Enum

    //@NonNull(message = "O nome do produto é essencial para identificação")
    private String name;
    private String description;

    //@Positive(message = "Preço obrigatoriamente deve ser positivo")
    private BigDecimal price;

    //@Positive(message = "Esta quantidade refere-se a sua quantidade disponivel em estoque, e deve ser positiva")
    private Long qtyAvailable;

    private Long qtySold; //defult = 0L
    private Long qtyRented; //defult = 0L
    private Long qtyRentedInProgress; //defult = 0L
    private LocalDateTime lastMove;

    @CreationTimestamp
    private LocalDateTime createdAt;


    //This will make sure that, when the product will is created, it starts as defult = 0
    @PrePersist
    protected void initializeDefaults() {
        if (this.qtySold == null && this.qtyRented == null && this.qtyRentedInProgress == null) {
            this.qtySold = 0L;
            this.qtyRented = 0L;
            this.qtyRentedInProgress = 0L;
        }
    }

}

