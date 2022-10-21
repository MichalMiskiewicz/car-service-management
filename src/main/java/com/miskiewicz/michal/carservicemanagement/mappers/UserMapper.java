package com.miskiewicz.michal.carservicemanagement.mappers;

import com.miskiewicz.michal.carservicemanagement.DTOs.UserDTO;
import com.miskiewicz.michal.carservicemanagement.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings({
      @Mapping(source = "user.firstname", target = "firstname"),
      @Mapping(source = "user.lastname", target = "lastname"),
      @Mapping(source = "user.car", target = "car")
    })
    UserDTO mapUserToUserDTO(User user);
}
