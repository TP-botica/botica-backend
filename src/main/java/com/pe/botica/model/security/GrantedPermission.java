package com.pe.botica.model.security;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.pe.botica.model.compoundId.GrantedPermissionId;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "granted_permission")
@Data
@IdClass(GrantedPermissionId.class)
public class GrantedPermission {
    @Id
    @ManyToOne
    @JoinColumn(name = "role_id")
    @JsonBackReference(value = "granted_permission-role")
    private Role role;
    @Id
    @ManyToOne
    @JoinColumn(name = "operation_id")
    @JsonBackReference(value = "granted_permission-operation")
    private Operation operation;
}
