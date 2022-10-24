package com.miskiewicz.michal.carservicemanagement.mappers;

import com.miskiewicz.michal.carservicemanagement.DTOs.UserDTO;
import com.miskiewicz.michal.carservicemanagement.entities.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
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
        return modelMapper;
    }
}