package com.miskiewicz.michal.carservicemanagement.services;

import com.miskiewicz.michal.carservicemanagement.DTOs.UserDTO;
import com.miskiewicz.michal.carservicemanagement.entities.User;
import com.miskiewicz.michal.carservicemanagement.exceptions.UserAlreadyExistsException;

import java.util.List;
import java.util.UUID;

public interface IUserService {
    UserDTO addUser(User user) throws UserAlreadyExistsException;
    List<UserDTO> getAllUsers();
    UserDTO getUserById(UUID uuid);
    
}
