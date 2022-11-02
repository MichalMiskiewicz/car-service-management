package com.miskiewicz.michal.carservicemanagement.repositories;

import com.miskiewicz.michal.carservicemanagement.CarServiceManagementApplication;
import com.miskiewicz.michal.carservicemanagement.entities.Address;
import com.miskiewicz.michal.carservicemanagement.entities.Car;
import com.miskiewicz.michal.carservicemanagement.entities.User;
import com.miskiewicz.michal.carservicemanagement.entities.enums.UserType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = CarServiceManagementApplication.class)
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    CarRepository carRepository;
    @Autowired
    AddressRepository addressRepository;
    User user;
    Car car;
    Address address;

    @BeforeEach
    void initialize() {
        car = new Car("type",
                "brand",
                "model",
                "registrationNumber",
                "EXAMPLEVINNUMBER");
        address = new Address(
                "streetExample",
                "00",
                "00-000",
                "cityExample");
        user = new User("firstName",
                "lastName",
                "email@email.com",
                "password",
                UserType.CLIENT,
                address,
                List.of(car));
        carRepository.save(car);
        addressRepository.save(address);
        userRepository.save(user);
    }

    @Test
    void itShouldReturnUserByEmail() {
        Optional<User> optionalUser = userRepository.getUserByEmail("email@email.com");

        assertTrue(optionalUser.isPresent());
        assertThat(optionalUser.get().getEmail()).isEqualTo("email@email.com");

    }

    @AfterEach
    void remove() {
        userRepository.delete(user);
        addressRepository.delete(address);
        carRepository.delete(car);
    }
}