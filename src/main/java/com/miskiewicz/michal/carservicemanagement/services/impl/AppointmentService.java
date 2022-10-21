package com.miskiewicz.michal.carservicemanagement.services.impl;

import com.miskiewicz.michal.carservicemanagement.DTOs.AppointmentDTO;
import com.miskiewicz.michal.carservicemanagement.entities.Appointment;
import com.miskiewicz.michal.carservicemanagement.mappers.AppointmentMapper;
import com.miskiewicz.michal.carservicemanagement.repositories.AppointmentRepository;
import com.miskiewicz.michal.carservicemanagement.services.AppointmentServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AppointmentService implements AppointmentServiceInterface {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;

    @Override
    public List<AppointmentDTO> getAllRepairs() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return appointments.stream().map(appointmentMapper::mapRepairToRepairDTO).collect(Collectors.toList());
    }
}
