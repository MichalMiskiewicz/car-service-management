package com.miskiewicz.michal.carservicemanagement.controllers;

import com.miskiewicz.michal.carservicemanagement.DTOs.AppointmentDTO;
import com.miskiewicz.michal.carservicemanagement.services.impl.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @GetMapping("/all")
    public ResponseEntity<List<AppointmentDTO>> getAllRepairs(){
        return ResponseEntity.ok(appointmentService.getAllRepairs());
    }

    @PostMapping("/add")
    public ResponseEntity<AppointmentDTO> addAppointment(){

        return null;
    }
}
