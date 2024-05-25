package com.pe.botica.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class DrugstoreLocationsDTO {
    private String name;
    private String latitude;
    private String longitude;
    private UUID roleId;
}
