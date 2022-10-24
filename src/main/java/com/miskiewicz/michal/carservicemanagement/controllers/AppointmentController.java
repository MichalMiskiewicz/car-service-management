package com.miskiewicz.michal.carservicemanagement.controllers;

import com.miskiewicz.michal.carservicemanagement.DTOs.AppointmentDTO;
import com.miskiewicz.michal.carservicemanagement.entities.Appointment;
import com.miskiewicz.michal.carservicemanagement.services.impl.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @GetMapping("/all")
    public ResponseEntity<List<AppointmentDTO>> getAllRepairs(){
        return ResponseEntity.ok(appointmentService.getAllRepairs());
    }

    @PostMapping("/add/{carId}")
    public ResponseEntity<AppointmentDTO> addAppointment(@RequestBody Appointment appointment, @PathVariable UUID carId) throws Exception {
        try{
            return ResponseEntity.ok().body(appointmentService.addAppointment(appointment, carId));
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

    }

    @PatchMapping("finished/{appointmentId}")
    public AppointmentDTO setFinishedDate(@PathVariable UUID appointmentId){
        try {
            return appointmentService.setFinishedDate(appointmentId);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
        }
    }


}
