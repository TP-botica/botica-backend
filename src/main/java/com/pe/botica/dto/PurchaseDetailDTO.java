package com.pe.botica.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PurchaseDetailDTO {
    private UUID purchaseId;
    private UUID productId;
    private UUID serviceId;
    private Integer quantity;
    private Float price;
    private Float discount;
}
