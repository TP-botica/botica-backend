package com.pe.botica.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class DrugstoreServiceDTO {
    private UUID drugstoreId;
    private UUID serviceId;
    private Float price;
}
