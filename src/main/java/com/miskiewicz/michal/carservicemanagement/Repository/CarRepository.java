package com.miskiewicz.michal.carservicemanagement.Repository;

import com.miskiewicz.michal.carservicemanagement.Entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CarRepository extends JpaRepository<Car, UUID> {
}
