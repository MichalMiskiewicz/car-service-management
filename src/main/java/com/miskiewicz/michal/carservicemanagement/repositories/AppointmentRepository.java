package com.miskiewicz.michal.carservicemanagement.repositories;

import com.miskiewicz.michal.carservicemanagement.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {
    @Query(value = "select * from appointment a join car c on a.car_id = c.id where c.vin_number = :vinNumber"
            , nativeQuery = true)
    List<Appointment> getAppointmentByVinNumber(@Param("vinNumber") String vinNumber);
    @Query(value = "select * from appointment a " +
            "join car c on a.car_id = c.id " +
            "where (:brand is null or c.brand = :brand)" +
            "and (:model is null or c.model = :model)"
            , nativeQuery = true)
    List<Appointment> getAppointmentsByBrandAndModel(@Param("brand") String brand, @Param("model") String model);
}
