package com.pe.botica.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class MyServicesViewDTO {
    private UUID serviceId;
    private String name;
    private String imageUrl;
    private Float price;
    private String category;
}