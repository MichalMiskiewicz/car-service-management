package com.miskiewicz.michal.carservicemanagement.services;

import com.miskiewicz.michal.carservicemanagement.DTOs.AppointmentDTO;
import com.miskiewicz.michal.carservicemanagement.entities.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IAppointmentService {
    Page<AppointmentDTO> getAllAppointments(Pageable page);

    AppointmentDTO addAppointment(Appointment appointment, UUID carId);

    AppointmentDTO setFinishedDate(UUID appointmentId);

    List<AppointmentDTO> getAppointmentsByType(String type);

    List<AppointmentDTO> getAppointmentsByVinNumber(String vinNumber);

    List<AppointmentDTO> getAppointmentsByBrandAndModel(String brand, String model);
}
