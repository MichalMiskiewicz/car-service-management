package com.miskiewicz.michal.carservicemanagement.services.impl;

import com.miskiewicz.michal.carservicemanagement.DTOs.AppointmentDTO;
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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.swing.text.html.Option;
import javax.validation.constraints.Null;
import java.sql.Timestamp;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class AppointmentServiceTest {

    @Mock
    private AppointmentRepository appointmentRepository;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private AppointmentService appointmentService;
    private Appointment appointment;

    @BeforeEach
    void setUp() {
        AppointmentType appointmentType = new AppointmentType("exampleType");
        Car car = new Car("type",
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
        given(appointmentRepository.findAll(PageRequest.ofSize(2))).willReturn(new PageImpl<>(List.of(appointment)));
        given(modelMapper.map(appointment, AppointmentDTO.class))
                .willReturn(new AppointmentDTO(
                        UUID.fromString("00000000-0000-0000-0000-000000000001"), new Timestamp(System.currentTimeMillis()),
                        new Timestamp(System.currentTimeMillis()), "", "", "",
                        "EXAMPLEVINNUMBER", ""));

        Page<AppointmentDTO> app = appointmentService.getAllAppointments(PageRequest.ofSize(2));

        assertThat(appointment.getCar().getVinNumber()).isEqualTo(app.getContent().get(0).getVinNumber());

    }

    @Test
    void addAppointment() {
    }

    @Test
    void willThrowWhenAppointmentDoesNotExist() {
        given(appointmentRepository.findById(any()))
                .willReturn(Optional.empty());

        assertThatThrownBy(() -> appointmentService.setFinishedDate(any()))
                .isInstanceOf(Exception.class)
                .hasMessageContaining("There is no Exception with that ID!");
    }

    @Test
    void willThrowWhenAppointmentFinishedDateExists() {
        appointment.setFinishedDate(new Timestamp(System.currentTimeMillis()));
        given(appointmentRepository.findById(any()))
                .willReturn(Optional.of(appointment));

        assertThatThrownBy(() -> appointmentService.setFinishedDate(any()))
                .isInstanceOf(Exception.class)
                .hasMessageContaining("The appointment has been finished already!");
    }

    @Test
    void willThrowWhenAppointmentsDoNotExist() {
        given(appointmentRepository.findAll())
                .willReturn(Collections.emptyList());

        assertThatThrownBy(() -> appointmentService.getAppointmentsByType("type"))
                .isInstanceOf(Exception.class)
                .hasMessageContaining("There is no appointments of type: " + "type");
    }

    @Test
    void canGetAppointmentsByType() {
        given(appointmentRepository.findAll())
                .willReturn(List.of(appointment));
        given(modelMapper.map(appointment, AppointmentDTO.class)).willReturn(new AppointmentDTO(
                UUID.fromString("00000000-0000-0000-0000-000000000001"), new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis()), "", "", "",
                "EXAMPLEVINNUMBER", "exampleType"));

        List<AppointmentDTO> appointments = appointmentService.getAppointmentsByType("exampleType");

        assertThat(appointment.getAppointmentType().getName()).isEqualTo(appointments.get(0).getAppointmentType());
    }
}