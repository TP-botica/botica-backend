package com.pe.botica.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "role")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "name", length = 100)
    private String name;
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "role-user")
    private List<User> users;
}
