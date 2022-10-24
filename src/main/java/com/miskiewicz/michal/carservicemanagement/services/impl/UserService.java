package com.miskiewicz.michal.carservicemanagement.services.impl;

import com.miskiewicz.michal.carservicemanagement.DTOs.UserDTO;
import com.miskiewicz.michal.carservicemanagement.entities.Address;
import com.miskiewicz.michal.carservicemanagement.entities.Car;
import com.miskiewicz.michal.carservicemanagement.entities.User;
import com.miskiewicz.michal.carservicemanagement.repositories.AddressRepository;
import com.miskiewicz.michal.carservicemanagement.repositories.CarRepository;
import com.miskiewicz.michal.carservicemanagement.repositories.UserRepository;
import com.miskiewicz.michal.carservicemanagement.services.UserServiceInterface;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService implements UserServiceInterface {

    private final UserRepository userRepository;
    private final CarRepository carRepository;
    private final AddressRepository addressRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDTO addUser(User user) throws Exception {
        Optional<User> optionalUser = userRepository.getUserByEmail(user.getEmail());
        if (optionalUser.isEmpty()) {
            checkAddressExists(user);
            checkCarsExists(user);
            User savedUser = userRepository.save(user);
            return modelMapper.map(savedUser, UserDTO.class);
        } else {
            throw new Exception("This user already exists!");
        }
    }

    private void checkAddressExists(User user) {
        Optional<Address> optionalAddress =
                addressRepository.getAddressByProvidedData(
                        user.getAddress().getCity(),
                        user.getAddress().getStreet(),
                        user.getAddress().getHouseNumber()
                );
        if (optionalAddress.isEmpty()) {
            addressRepository.save(user.getAddress());
        } else {
            user.setAddress(optionalAddress.get());
        }
    }

    private void checkCarsExists(User user) {
        List<Car> cars = new ArrayList<>();
        user.getCar().forEach(car -> {
            Optional<Car> carByVinNumber = carRepository.getByVinNumber(car.getVinNumber());
            if (carByVinNumber.isEmpty()) {
                carRepository.save(car);
            } else {
                cars.add(carByVinNumber.get());
                user.setCar(cars);
            }
        });
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> allUsers = userRepository.findAll();
        return allUsers.stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
    }
}
