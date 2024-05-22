package com.pe.botica.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ServiceDTO {
    private String name;
    private String description;
    private Boolean state;
    private String imageUrl;
    private UUID categoryId;
}
