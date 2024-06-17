package com.pe.botica.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ServiceDetailDTO {
    private String name;
    private String description;
    private String imageUrl;
    private String category;
}
