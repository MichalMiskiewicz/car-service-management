package com.miskiewicz.michal.carservicemanagement.mappers;

import com.miskiewicz.michal.carservicemanagement.DTOs.AppointmentDTO;
import com.miskiewicz.michal.carservicemanagement.DTOs.UserDTO;
import com.miskiewicz.michal.carservicemanagement.entities.Appointment;
import com.miskiewicz.michal.carservicemanagement.entities.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    @Bean
    public ModelMapper getModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new PropertyMap<User, UserDTO>() {
            @Override
            protected void configure() {
                map().setCity(source.getAddress().getCity());
                map().setAddressId(source.getAddress().getId());
            }
        });

        modelMapper.addMappings(new PropertyMap<Appointment, AppointmentDTO>() {
            @Override
            protected void configure() {
                map().setAppointmentType(source.getAppointmentType().getName());
                map().setVinNumber(source.getCar().getVinNumber());
            }
        });
        return modelMapper;
    }
}