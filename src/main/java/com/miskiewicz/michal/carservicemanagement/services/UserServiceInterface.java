package com.miskiewicz.michal.carservicemanagement.services;

import com.miskiewicz.michal.carservicemanagement.DTOs.UserDTO;
import com.miskiewicz.michal.carservicemanagement.entities.User;
import org.mapstruct.control.MappingControl;

public interface UserServiceInterface {
    UserDTO addUser(User user);
    User getUser();
}
