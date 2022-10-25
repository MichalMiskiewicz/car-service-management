package com.miskiewicz.michal.carservicemanagement.controllers;

import com.miskiewicz.michal.carservicemanagement.DTOs.AppointmentDTO;
import com.miskiewicz.michal.carservicemanagement.entities.Appointment;
import com.miskiewicz.michal.carservicemanagement.services.impl.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<Page<AppointmentDTO>> getAllAppointments(Pageable page){
        return ResponseEntity.ok(appointmentService.getAllAppointments(page));
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
    public ResponseEntity<AppointmentDTO> setFinishedDate(@PathVariable UUID appointmentId){
        try {
            return ResponseEntity.ok().body(appointmentService.setFinishedDate(appointmentId));
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<AppointmentDTO>> getAppointmentsByType(@RequestParam String type){
        try{
            return ResponseEntity.ok().body(appointmentService.getAppointmentsByType(type));
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/car/vin/{vinNumber}")
    public ResponseEntity<List<AppointmentDTO>> getAppointmentsByVinNumber(@PathVariable("vinNumber") String vinNumber){
        try{
            return ResponseEntity.ok().body(appointmentService.getAppointmentsByVinNumber(vinNumber));
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
    @GetMapping("/car")
    public ResponseEntity<List<AppointmentDTO>> getAppointmentsByBrandAndModel(
            @RequestParam(value = "brand", required=false) String brand,
            @RequestParam(value = "model", required=false) String model){
       try {
           return ResponseEntity.ok().body(appointmentService.getAppointmentsByBrandAndModel(brand, model));
       }catch (Exception e){
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
       }
    }


}
