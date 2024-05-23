package com.pe.botica.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class DrugstoreProductDTO {
    private UUID drugstoreId;
    private UUID productId;
    private Float price;
    private Integer stock;
}
