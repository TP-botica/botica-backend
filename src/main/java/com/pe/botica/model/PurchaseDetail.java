package com.pe.botica.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "purchase_detail")
@Data
public class PurchaseDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "purchase_id")
    @JsonBackReference
    private Purchase purchase;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = true)
    private Product product;
    @ManyToOne
    @JoinColumn(name = "service_id", nullable = true)
    private Service service;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "price")
    private Float price;
    @Column(name = "discount")
    private Float discount;
}
