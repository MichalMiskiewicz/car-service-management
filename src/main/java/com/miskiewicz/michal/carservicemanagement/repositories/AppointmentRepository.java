package com.miskiewicz.michal.carservicemanagement.repositories;

import com.miskiewicz.michal.carservicemanagement.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {
}
