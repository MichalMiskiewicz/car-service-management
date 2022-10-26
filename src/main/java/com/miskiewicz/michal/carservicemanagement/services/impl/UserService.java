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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService implements UserServiceInterface {

    private final UserRepository userRepository;
    private final CarRepository carRepository;
    private final AddressRepository addressRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDTO addUser(User user) throws Exception {
        Optional<User> optionalUser = userRepository.getUserByEmail(user.getEmail());
        if (optionalUser.isEmpty()) {
            checkAddressExists(user);
            checkCarExists(user);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User savedUser = userRepository.save(user);
            return modelMapper.map(savedUser, UserDTO.class);
        } else {
            throw new Exception("This user already exists!");
        }
    }

    @Override
    public UserDTO getUserById(UUID uuid) throws Exception {
        Optional<User> optionalUser = userRepository.findById(uuid);
        if(optionalUser.isEmpty()){
            throw new Exception("There is no user with that ID");
        }else {
            return modelMapper.map(optionalUser.get(), UserDTO.class);
        }
    }

    private void checkAddressExists(User user) throws Exception {
        if(user.getAddress() == null){
            throw new Exception("There is no address provided!");
        }else {
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
    }

    private void checkCarExists(User user) throws Exception {
        if(user.getCar() == null){
            throw new Exception("There is no information about a car!");
        }else {
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
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> allUsers = userRepository.findAll();
        return allUsers.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }
}
