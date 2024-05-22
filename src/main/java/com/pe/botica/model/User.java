package com.pe.botica.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "name", length = 100)
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "password", length = 100)
    private String password;
    @Column(name = "latitude")
    private String latitude;
    @Column(name = "longitude")
    private String longitude;
    @ManyToOne
    @JoinColumn(name = "role_id")
    @JsonBackReference(value = "role-user")
    private Role role;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "drugstore-product")
    private List<DrugstoreProduct> drugstoreProducts;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "drugstore-service")
    private List<DrugstoreService> drugstoreServices;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "customer-purchase")
    private List<Purchase> customerPurchases;
    @OneToMany(mappedBy = "drugstore", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "drugstore-purchase")
    private List<Purchase> drugstorePurchases;
}
