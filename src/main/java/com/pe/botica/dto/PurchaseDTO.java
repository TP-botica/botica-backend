package com.pe.botica.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PurchaseDTO {
    private UUID customerId;
    private UUID drugstoreId;
    private Float total;
}
