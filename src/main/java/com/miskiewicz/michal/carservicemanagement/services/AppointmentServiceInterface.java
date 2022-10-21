package com.miskiewicz.michal.carservicemanagement.services;

import com.miskiewicz.michal.carservicemanagement.DTOs.AppointmentDTO;

import java.util.List;

public interface AppointmentServiceInterface {

    List<AppointmentDTO> getAllRepairs();
}
