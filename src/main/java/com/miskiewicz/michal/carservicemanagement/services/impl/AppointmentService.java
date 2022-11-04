package com.miskiewicz.michal.carservicemanagement.services.impl;

import com.miskiewicz.michal.carservicemanagement.DTOs.AppointmentDTO;
import com.miskiewicz.michal.carservicemanagement.entities.Appointment;
import com.miskiewicz.michal.carservicemanagement.entities.Car;
import com.miskiewicz.michal.carservicemanagement.repositories.AppointmentRepository;
import com.miskiewicz.michal.carservicemanagement.repositories.AppointmentTypeRepository;
import com.miskiewicz.michal.carservicemanagement.repositories.CarRepository;
import com.miskiewicz.michal.carservicemanagement.services.IAppointmentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AppointmentService implements IAppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final ModelMapper modelMapper;
    private final CarRepository carRepository;
    private final AppointmentTypeRepository appointmentTypeRepository;

    @Override
    public Page<AppointmentDTO> getAllAppointments(Pageable page) {
        Page<Appointment> appointments = appointmentRepository.findAll(page);
        return appointments
                .map(appointment -> modelMapper.map(appointment, AppointmentDTO.class));
    }

    @Override
    public AppointmentDTO addAppointment(Appointment appointment, UUID carId) {
        Optional<Car> optionalCar = carRepository.findById(carId);

        if(appointment.getAppointmentType() == null){
            throw new NullPointerException("There is no appointment type provided");
        }

        if(optionalCar.isEmpty()){
            throw new NullPointerException("There is no car in Database");
        }

        appointmentTypeRepository.save(appointment.getAppointmentType());
        appointment.setCar(optionalCar.get());
        appointmentRepository.save(appointment);

        return modelMapper.map(appointment, AppointmentDTO.class);
    }

    @Override
    public AppointmentDTO setFinishedDate(UUID appointmentId) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointmentId);
        if (optionalAppointment.isEmpty()){
            throw new NullPointerException("There is no Exception with that ID!");
        }

        if (optionalAppointment.get().getFinishedDate() != null
                && !optionalAppointment.get().getFinishedDate().toString().equals("")){
            throw new NullPointerException("The appointment has been finished already!");
        }

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat currentDate =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        optionalAppointment.get().setFinishedDate(Timestamp.valueOf(currentDate.format(timestamp)));

        Appointment updatedAppointment = appointmentRepository.save(optionalAppointment.get());
        return modelMapper.map(updatedAppointment, AppointmentDTO.class);
    }

    @Override
    public List<AppointmentDTO> getAppointmentsByType(String type) {
        List<Appointment> optionalAppointments = appointmentRepository.findAll();


        List<Appointment> appointmentsList = optionalAppointments
                .stream()
                .filter(appointment -> appointment.getAppointmentType() != null &&
                        appointment.getAppointmentType().getName().equals(type)).toList();

        if (appointmentsList.isEmpty()){
            throw new NullPointerException("There is no appointments of type: " + type);
        }

        return appointmentsList
                .stream()
                .map(appointment -> modelMapper.map(appointment, AppointmentDTO.class))
                .collect(Collectors.toList());

    }

    @Override
    public List<AppointmentDTO> getAppointmentsByVinNumber(String vinNumber) {
        List<Appointment> appointments = appointmentRepository.getAppointmentByVinNumber(vinNumber);
        if (appointments.isEmpty()){
            throw new NullPointerException("There is no appointments of car: " + vinNumber);
        }

        return appointments
                .stream()
                .map(appointment -> modelMapper.map(appointment, AppointmentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> getAppointmentsByBrandAndModel(String brand, String model) {
        List<Appointment> appointments = appointmentRepository.getAppointmentsByBrandAndModel(brand, model);
        if (appointments.isEmpty()){
            throw new NullPointerException("There is no appointments with: " + brand + " " + model);
        }

        return appointments.stream()
                .map(appointment -> modelMapper.map(appointment, AppointmentDTO.class))
                .collect(Collectors.toList());
    }


}

