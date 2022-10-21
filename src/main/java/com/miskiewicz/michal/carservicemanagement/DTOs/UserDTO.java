package com.miskiewicz.michal.carservicemanagement.DTOs;

import com.miskiewicz.michal.carservicemanagement.entities.Address;
import com.miskiewicz.michal.carservicemanagement.entities.Car;
import com.miskiewicz.michal.carservicemanagement.repositories.CarRepository;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class UserDTO {

    private String firstname;

    private String lastname;

    private String email;

    private List<Car> car;

    private Address address;

    private String userType;
}
