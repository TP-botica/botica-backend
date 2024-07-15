package com.pe.botica.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserDataDTO {
    private UUID id;
    private String name;
    private String email;
    private String role;
}
