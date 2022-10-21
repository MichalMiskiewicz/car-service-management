package com.miskiewicz.michal.carservicemanagement.services.impl;

import com.miskiewicz.michal.carservicemanagement.DTOs.UserDTO;
import com.miskiewicz.michal.carservicemanagement.entities.User;
import com.miskiewicz.michal.carservicemanagement.mappers.UserMapper;
import com.miskiewicz.michal.carservicemanagement.repositories.AddressRepository;
import com.miskiewicz.michal.carservicemanagement.repositories.CarRepository;
import com.miskiewicz.michal.carservicemanagement.repositories.UserRepository;
import com.miskiewicz.michal.carservicemanagement.services.UserServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserServiceInterface {

    private final UserRepository userRepository;
    private final CarRepository carRepository;
    private final AddressRepository addressRepository;
    private final UserMapper userMapper;

    public UserDTO addUser(User user) {

        carRepository.save(user.getCar().get(0));
        addressRepository.save(user.getAddress());
        User save = userRepository.save(user);
        return userMapper.mapUserToUserDTO(save);
    }

    @Override
    public User getUser() {
        return null;
    }
}
