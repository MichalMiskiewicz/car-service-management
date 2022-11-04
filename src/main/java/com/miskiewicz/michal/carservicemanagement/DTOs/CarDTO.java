package com.miskiewicz.michal.carservicemanagement.DTOs;

import lombok.Data;

import java.util.UUID;

@Data
public class CarDTO {

    private UUID id;

    private String type;

    private String brand;

    private String registrationNumber;

    private String VinNumber;
}
