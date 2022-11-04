package com.miskiewicz.michal.carservicemanagement.services.impl;

import com.miskiewicz.michal.carservicemanagement.entities.Appointment;
import com.miskiewicz.michal.carservicemanagement.entities.AppointmentType;
import com.miskiewicz.michal.carservicemanagement.entities.Car;
import com.miskiewicz.michal.carservicemanagement.entities.User;
import com.miskiewicz.michal.carservicemanagement.repositories.AppointmentRepository;
import com.miskiewicz.michal.carservicemanagement.repositories.AppointmentTypeRepository;
import com.miskiewicz.michal.carservicemanagement.repositories.CarRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.sql.Timestamp;

@ExtendWith(MockitoExtension.class)
class AppointmentServiceTest {

    @Mock
    private AppointmentRepository appointmentRepository;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private CarRepository carRepository;
    @Mock
    private AppointmentTypeRepository appointmentTypeRepository;
    @Mock
    private AppointmentService appointmentService;
    private User user;
    private Car car;
    private Appointment appointment;
    private AppointmentType appointmentType;

    @BeforeEach
    void setUp() {
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

    }

    @Test
    void canGetAllAppointments() {
    }

    @Test
    void addAppointment() {
    }

    @Test
    void setFinishedDate() {
    }

    @Test
    void getAppointmentsByType() {
    }

    @Test
    void getAppointmentsByVinNumber() {
    }

    @Test
    void getAppointmentsByBrandAndModel() {
    }

    @AfterEach
    void tearDown() {

    }
}