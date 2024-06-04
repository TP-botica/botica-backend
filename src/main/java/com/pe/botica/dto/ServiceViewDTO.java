package com.pe.botica.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class ServiceViewDTO {
    private String name;
    private String description;
    private Float price;
    private String drugstoreName;
    private String imageUrl;
}
