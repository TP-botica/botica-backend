package com.pe.botica.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
public class ProductDTO {
    private String name;
    private String description;
    private Boolean state;
    private Boolean prescriptionRequired;
    private String imageUrl;
    private UUID categoryId;
}
