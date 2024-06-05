package com.pe.botica.dto.auth;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserLoginDTO {
    private String email;
    private String password;
}
