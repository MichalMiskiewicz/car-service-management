package com.miskiewicz.michal.carservicemanagement.repositories;

import com.miskiewicz.michal.carservicemanagement.CarServiceManagementApplication;
import com.miskiewicz.michal.carservicemanagement.entities.Appointment;
import com.miskiewicz.michal.carservicemanagement.entities.AppointmentType;
import com.miskiewicz.michal.carservicemanagement.entities.Car;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(classes = CarServiceManagementApplication.class)
class AppointmentRepositoryTest {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private AppointmentTypeRepository appointmentTypeRepository;
    @Autowired
    private CarRepository carRepository;
    private Appointment appointment;
    private AppointmentType appointmentType;
    private Car car;

    @BeforeEach
    void initialize() {
        appointmentType = new AppointmentType("exampleType");
        car = new Car("type",
                "brand",
                "model",
                "registrationNumber",
                "EXAMPLEVINNUMBER");
        appointment = new Appointment(
                new Timestamp(System.currentTimeMillis()),
                null,
                "example description",
                appointmentType,
                car);
        carRepository.save(car);
        appointmentTypeRepository.save(appointmentType);
        appointmentRepository.save(appointment);
    }

    @Test
    void itShouldReturnAppointmentByVinNumber() {
        List<Appointment> appointments = appointmentRepository
                .getAppointmentByVinNumber("EXAMPLEVINNUMBER");

        appointments.forEach(x ->
                assertThat(x.getCar().getVinNumber()).isEqualTo("EXAMPLEVINNUMBER")
        );

    }

    @Test
    void itShouldReturnAppointmentsByBrandAndModel() {
        List<Appointment> appointments = appointmentRepository
                .getAppointmentsByBrandAndModel("brand", "model");

        appointments.forEach(x -> {
            assertThat(x.getCar().getBrand()).isEqualTo("brand");
            assertThat(x.getCar().getModel()).isEqualTo("model");
        });
    }

    @AfterEach
    void remove() {
        appointmentRepository.delete(appointment);
        appointmentTypeRepository.delete(appointmentType);
        carRepository.delete(car);
    }
}