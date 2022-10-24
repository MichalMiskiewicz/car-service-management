package com.miskiewicz.michal.carservicemanagement.DTOs;

import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class AppointmentDTO {

    private UUID id;

    private String ownerFirstname;

    private String ownerLastname;

    private Timestamp approvedDate;

    private Timestamp finishedDate;

    private String description;

    private String carBrand;

    private String carModel;

    private String vinNumber;

    private String appointmentType;
}
