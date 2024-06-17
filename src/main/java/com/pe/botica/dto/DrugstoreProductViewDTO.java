package com.pe.botica.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class DrugstoreProductViewDTO {
    private String drugstore;
    private String product;
    private Float price;
    private Integer stock;
}
