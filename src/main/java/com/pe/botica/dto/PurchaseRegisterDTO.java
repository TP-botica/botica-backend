package com.pe.botica.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class PurchaseRegisterDTO {
    private UUID customerId;
    private UUID drugstoreId;
    private List<PurchaseDetailDTO> purchaseDetails;
}
