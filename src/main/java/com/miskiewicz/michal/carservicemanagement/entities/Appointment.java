package com.miskiewicz.michal.carservicemanagement.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Appointment extends DefaultEntity {

    private Timestamp approvedDate;

    private Timestamp finishedDate;

    private String description;

    @ManyToOne
    @JoinColumn(name = "appointment_type_id")
    private AppointmentType appointmentType;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

}