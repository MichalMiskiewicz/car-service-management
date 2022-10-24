package com.miskiewicz.michal.carservicemanagement.services.impl;

import com.miskiewicz.michal.carservicemanagement.DTOs.AppointmentDTO;
import com.miskiewicz.michal.carservicemanagement.entities.Appointment;
import com.miskiewicz.michal.carservicemanagement.entities.Car;
import com.miskiewicz.michal.carservicemanagement.repositories.AppointmentRepository;
import com.miskiewicz.michal.carservicemanagement.repositories.AppointmentTypeRepository;
import com.miskiewicz.michal.carservicemanagement.repositories.CarRepository;
import com.miskiewicz.michal.carservicemanagement.services.AppointmentServiceInterface;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AppointmentService implements AppointmentServiceInterface {

    private final AppointmentRepository appointmentRepository;
    private final ModelMapper modelMapper;
    private final CarRepository carRepository;
    private final AppointmentTypeRepository appointmentTypeRepository;

    @Override
    public List<AppointmentDTO> getAllRepairs() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return appointments.stream().map(appointment -> modelMapper.map(appointment, AppointmentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public AppointmentDTO addAppointment(Appointment appointment, UUID carId) throws Exception {
        Optional<Car> optionalCar = carRepository.findById(carId);

        if(appointment.getAppointmentType() == null){
            throw new Exception("There is no appointment type provided");
        }

        if(optionalCar.isEmpty()){
            throw new Exception("There is no car in Database");
        }

        appointmentTypeRepository.save(appointment.getAppointmentType());
        appointment.setCar(optionalCar.get());
        appointmentRepository.save(appointment);

        return modelMapper.map(appointment, AppointmentDTO.class);
    }

    @Override
    public AppointmentDTO setFinishedDate(UUID appointmentId) throws Exception {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointmentId);
        if (optionalAppointment.isEmpty()){
            throw new Exception("There is no Exception with that ID!");
        }

        if (!optionalAppointment.get().getFinishedDate().toString().equals("")){
            throw new Exception("The appointment has been finished already!");
        }

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat currentDate =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        optionalAppointment.get().setFinishedDate(Timestamp.valueOf(currentDate.format(timestamp)));

        Appointment updatedAppointment = appointmentRepository.save(optionalAppointment.get());
        return modelMapper.map(updatedAppointment, AppointmentDTO.class);
    }
}
