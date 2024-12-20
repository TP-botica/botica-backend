package com.pe.botica.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class DrugstoreLocationsDTO {
    private UUID drugstoreId;
    private String name;
    private String latitude;
    private String longitude;
}
