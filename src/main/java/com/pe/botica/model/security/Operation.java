package com.pe.botica.model.security;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "operation")
@Data
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String path;
    private String httpMethod;
    private Boolean permitAll;
    @ManyToOne
    @JoinColumn(name = "module_id")
    private Module module;
}
