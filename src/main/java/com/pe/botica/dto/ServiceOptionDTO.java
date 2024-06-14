package com.pe.botica.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class ServiceOptionDTO {
    private UUID id;
    private String name;
}
