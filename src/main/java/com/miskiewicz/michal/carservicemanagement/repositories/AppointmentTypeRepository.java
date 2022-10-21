package com.miskiewicz.michal.carservicemanagement.repositories;

import com.miskiewicz.michal.carservicemanagement.entities.AppointmentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AppointmentTypeRepository extends JpaRepository<AppointmentType, UUID> {
}
