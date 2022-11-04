package com.miskiewicz.michal.carservicemanagement.repositories;

import com.miskiewicz.michal.carservicemanagement.CarServiceManagementApplication;
import com.miskiewicz.michal.carservicemanagement.entities.Address;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest(classes = CarServiceManagementApplication.class)
class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;
    private Address address;

    @BeforeEach
    public void initialize() {
        address = new Address("streetExample", "00", "00-000", "cityExample");
    }

    @Test
    public void itShouldReturnAddressByProvidedData() {
        //given
        addressRepository.save(address);

        //when
        Optional<Address> optionalAddress = addressRepository
                .getAddressByProvidedData("cityExample", "streetExample", "00");

        //then
        Assertions.assertTrue(optionalAddress.isPresent());
        assertThat(optionalAddress.get().getCity()).isEqualTo(address.getCity());
        assertThat(optionalAddress.get().getStreet()).isEqualTo(address.getStreet());
        assertThat(optionalAddress.get().getHouseNumber()).isEqualTo(address.getHouseNumber());

    }

    @AfterEach
    public void remove(){
        addressRepository.delete(address);
    }
}