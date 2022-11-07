package com.miskiewicz.michal.carservicemanagement.DTOs;

import lombok.*;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO {

    private UUID id;

    private Timestamp approvedDate;

    private Timestamp finishedDate;

    private String description;

    private String carBrand;

    private String carModel;

    private String vinNumber;

    private String appointmentType;
}
