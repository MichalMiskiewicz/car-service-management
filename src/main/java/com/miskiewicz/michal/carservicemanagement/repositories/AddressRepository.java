package com.miskiewicz.michal.carservicemanagement.repositories;

import com.miskiewicz.michal.carservicemanagement.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {

    @Query(value = "select * from address a where a.city = :city and a.street = :street and a.house_number = :houseNumber"
            , nativeQuery = true)
    Optional<Address> getAddressByProvidedData(@Param("city") String city,
                                               @Param("street") String street,
                                               @Param("houseNumber") String houseNumber);
}
