package com.pe.botica.model.compoundId;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class DrugstoreServiceId implements Serializable {
    private UUID service;
    private UUID user;
}
