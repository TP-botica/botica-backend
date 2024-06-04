package com.pe.botica.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class ProductViewDTO {
    private String name;
    private String description;
    private Float price;
    private Boolean prescriptionRequired;
    private String drugstoreName;
    private String imageUrl;
    private Integer stock;
}
