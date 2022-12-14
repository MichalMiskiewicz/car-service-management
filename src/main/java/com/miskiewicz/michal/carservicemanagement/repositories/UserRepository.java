package com.miskiewicz.michal.carservicemanagement.repositories;


import com.miskiewicz.michal.carservicemanagement.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    @Query(value = "select * from user where email = :email", nativeQuery = true)
    Optional<User> getUserByEmail(@Param("email") String email);
}
