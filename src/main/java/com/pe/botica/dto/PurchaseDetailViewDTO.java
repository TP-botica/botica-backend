package com.pe.botica.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class PurchaseDetailViewDTO {
    private String name;
    private Integer quantity;
    private Float price;
    private Float discount;
}
