package com.pe.botica.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "drugstore_service")
@Data
public class DrugstoreService {
    @Id
    @ManyToOne
    @JoinColumn(name = "drugstore_id")
    @JsonBackReference(value = "drugstore-service")
    private User user;
    @Id
    @ManyToOne
    @JoinColumn(name = "service_id")
    @JsonBackReference
    private Service service;
    @Column(name = "price")
    private Float price;
}
