package com.pe.botica.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.pe.botica.model.compoundId.DrugstoreServiceId;
import com.pe.botica.model.security.User;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "drugstore_service")
@Data
@IdClass(DrugstoreServiceId.class)
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
