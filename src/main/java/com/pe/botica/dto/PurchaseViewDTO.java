package com.pe.botica.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class PurchaseViewDTO {
    private UUID id;
    private String customer;
    private Date date;
    private Float total;
}
