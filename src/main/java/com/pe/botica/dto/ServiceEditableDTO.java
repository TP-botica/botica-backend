package com.pe.botica.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ServiceEditableDTO {
    private UUID serviceId;
    private UUID drugstoreId;
    private Float price;
}
