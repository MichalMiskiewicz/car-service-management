package com.miskiewicz.michal.carservicemanagement.repositories;

import com.miskiewicz.michal.carservicemanagement.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface CarRepository extends JpaRepository<Car, UUID> {

    @Query(value = "select * from car where vin_number = :vinNumber", nativeQuery = true)
    Optional<Car> getByVinNumber(@Param("vinNumber") String vinNumber);
}
