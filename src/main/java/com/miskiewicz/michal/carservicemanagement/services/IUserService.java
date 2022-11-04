package com.miskiewicz.michal.carservicemanagement.services;

import com.miskiewicz.michal.carservicemanagement.DTOs.UserDTO;
import com.miskiewicz.michal.carservicemanagement.entities.User;

import java.util.List;
import java.util.UUID;

public interface IUserService {
    UserDTO addUser(User user) throws Exception;
    List<UserDTO> getAllUsers();
    UserDTO getUserById(UUID uuid) throws Exception;
    
}
