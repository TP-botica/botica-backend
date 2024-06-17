package com.pe.botica.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DrugstoreServiceViewDTO {
    private String drugstore;
    private String service;
    private Float price;
}
