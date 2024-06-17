package com.pe.botica.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class ProductDetailDTO {
    private String name;
    private String description;
    private Boolean prescriptionRequired;
    private String imageUrl;
    private String category;
}
