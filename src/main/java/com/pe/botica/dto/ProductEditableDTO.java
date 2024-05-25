package com.pe.botica.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ProductEditableDTO {
    private UUID productId;
    private UUID drugstoreId;
    private Float price;
    private Integer stock;
}
