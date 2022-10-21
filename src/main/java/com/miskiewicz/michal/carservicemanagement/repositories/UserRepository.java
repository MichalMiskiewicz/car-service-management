package com.miskiewicz.michal.carservicemanagement.repositories;

import com.miskiewicz.michal.carservicemanagement.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
