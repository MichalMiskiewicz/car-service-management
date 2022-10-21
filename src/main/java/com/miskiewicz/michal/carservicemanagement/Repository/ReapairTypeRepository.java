package com.miskiewicz.michal.carservicemanagement.Repository;

import com.miskiewicz.michal.carservicemanagement.Entity.RepairType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReapairTypeRepository extends JpaRepository<RepairType, UUID> {
}
