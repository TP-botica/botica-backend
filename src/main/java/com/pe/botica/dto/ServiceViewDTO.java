package com.pe.botica.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ServiceViewDTO {
    private String name;
    private String description;
    private Float price;
    private String drugstoreName;
    private String imageUrl;
}
