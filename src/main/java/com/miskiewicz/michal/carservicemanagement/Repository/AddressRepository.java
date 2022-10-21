package com.miskiewicz.michal.carservicemanagement.Repository;

import com.miskiewicz.michal.carservicemanagement.Entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
}
