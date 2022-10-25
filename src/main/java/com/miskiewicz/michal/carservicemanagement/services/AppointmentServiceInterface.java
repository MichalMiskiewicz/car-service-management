package com.miskiewicz.michal.carservicemanagement.services;

import com.miskiewicz.michal.carservicemanagement.DTOs.AppointmentDTO;
import com.miskiewicz.michal.carservicemanagement.entities.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface AppointmentServiceInterface {
    Page<AppointmentDTO> getAllAppointments(Pageable page);

    AppointmentDTO addAppointment(Appointment appointment, UUID carId) throws Exception;

    AppointmentDTO setFinishedDate(UUID appointmentId) throws Exception;

    List<AppointmentDTO> getAppointmentsByType(String type) throws Exception;

    List<AppointmentDTO> getAppointmentsByVinNumber(String vinNumber) throws Exception;

    List<AppointmentDTO> getAppointmentsByBrandAndModel(String brand, String model) throws Exception;
}
