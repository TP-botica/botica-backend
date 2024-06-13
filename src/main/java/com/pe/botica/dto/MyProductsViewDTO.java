package com.pe.botica.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class MyProductsViewDTO {
    private UUID productId;
    private String name;
    private String imageUrl;
    private Float price;
    private String category;
    private Integer stock;
}
