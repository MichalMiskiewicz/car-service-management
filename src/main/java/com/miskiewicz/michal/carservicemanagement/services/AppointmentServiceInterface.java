package com.miskiewicz.michal.carservicemanagement.services;

import com.miskiewicz.michal.carservicemanagement.DTOs.AppointmentDTO;
import com.miskiewicz.michal.carservicemanagement.entities.Appointment;

import java.util.List;
import java.util.UUID;

public interface AppointmentServiceInterface {
    List<AppointmentDTO> getAllRepairs();

    AppointmentDTO addAppointment(Appointment appointment, UUID carId) throws Exception;

    AppointmentDTO setFinishedDate(UUID appointmentId) throws Exception;
}
