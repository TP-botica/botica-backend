package com.pe.botica.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserDTO {
    private String name;
    private String email;
    private String password;
    private String repeatedPassword;
    private String latitude;
    private String longitude;
    private String accountNumber;
    private UUID roleId;
}
