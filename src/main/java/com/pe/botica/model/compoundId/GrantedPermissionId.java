package com.pe.botica.model.compoundId;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class GrantedPermissionId {
    private UUID role;
    private UUID operation;
}
