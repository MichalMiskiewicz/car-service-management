package com.miskiewicz.michal.carservicemanagement.repositories;

import com.miskiewicz.michal.carservicemanagement.CarServiceManagementApplication;
import com.miskiewicz.michal.carservicemanagement.entities.Car;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = CarServiceManagementApplication.class)
class CarRepositoryTest {

    @Autowired
    private CarRepository carRepository;
    private Car car;

    @BeforeEach
    void initialize() {
        car = new Car("type",
                "brand",
                "model",
                "registrationNumber",
                "EXAMPLEVINNUMBER");
        carRepository.save(car);
    }

    @Test
    void itShouldReturnCarByVinNumber() {
        Optional<Car> carOptional = carRepository.getByVinNumber("EXAMPLEVINNUMBER");

        assertTrue(carOptional.isPresent());
        assertThat(carOptional.get().getVinNumber()).isEqualTo("EXAMPLEVINNUMBER");
    }

    @AfterEach
    void remove() {
        carRepository.delete(car);
    }
}