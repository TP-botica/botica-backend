package com.pe.botica.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@AllArgsConstructor
@Getter
@Setter
public class RoleDTO {
    private UUID id;
    private String name;

}
