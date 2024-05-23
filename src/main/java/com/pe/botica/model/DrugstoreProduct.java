package com.pe.botica.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.pe.botica.model.compoundId.DrugstoreProductId;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "drugstore_product")
@Data
@IdClass(DrugstoreProductId.class)
public class DrugstoreProduct {
    @Id
    @ManyToOne
    @JoinColumn(name = "drugstore_id")
    @JsonBackReference(value = "drugstore-product")
    private User user;
    @Id
    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private Product product;
    @Column(name = "price")
    private Float price;
    @Column(name = "stock")
    private Integer stock;
}
