package com.miskiewicz.michal.carservicemanagement.DTOs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Builder
@Getter
@Setter
public class CarDTO {

    private UUID id;

    private String type;

    private String brand;

    private String registrationNumber;

    private String VinNumber;
}
