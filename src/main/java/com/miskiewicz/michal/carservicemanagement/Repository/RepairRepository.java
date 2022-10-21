package com.miskiewicz.michal.carservicemanagement.Repository;

import com.miskiewicz.michal.carservicemanagement.Entity.Repair;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RepairRepository extends JpaRepository<Repair, UUID> {
}
