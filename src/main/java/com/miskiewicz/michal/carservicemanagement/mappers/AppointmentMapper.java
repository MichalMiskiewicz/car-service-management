package com.miskiewicz.michal.carservicemanagement.mappers;

import com.miskiewicz.michal.carservicemanagement.DTOs.AppointmentDTO;
import com.miskiewicz.michal.carservicemanagement.entities.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {

    @Mappings({
            @Mapping(source = "appointment.id", target = "id"),/*
            @Mapping(source = "repair.car.", target = "ownerFirstname"),
            @Mapping(source = "repair.car.", target = "ownerLastname"),*/
            @Mapping(source = "appointment.approvedDate", target = "approvedDate"),
            @Mapping(source = "appointment.finishedDate", target = "finishedDate"),
            @Mapping(source = "appointment.description", target = "description"),
            @Mapping(source = "appointment.description", target = "carBrand"),
            @Mapping(source = "appointment.car.model", target = "carModel"),
            @Mapping(source = "appointment.car.vinNumber", target = "vinNumber"),
            @Mapping(source = "appointment.appointmentType.name", target = "appointmentType")
    })
    AppointmentDTO mapRepairToRepairDTO(Appointment appointment);
}
