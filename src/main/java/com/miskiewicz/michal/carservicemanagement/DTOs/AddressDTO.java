package com.miskiewicz.michal.carservicemanagement.DTOs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Builder
@Getter
@Setter
public class AddressDTO {

    private UUID id;

    private String street;

    private String houseNumber;

    private String zipCode;

    private String City;
}