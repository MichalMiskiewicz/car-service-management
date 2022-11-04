package com.miskiewicz.michal.carservicemanagement.DTOs;

import lombok.Data;

import java.util.UUID;

@Data
public class AddressDTO {

    private UUID id;

    private String street;

    private String houseNumber;

    private String zipCode;

    private String City;
}
