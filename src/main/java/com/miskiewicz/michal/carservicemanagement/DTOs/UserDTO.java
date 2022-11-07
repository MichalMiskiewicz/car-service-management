package com.miskiewicz.michal.carservicemanagement.DTOs;

import com.miskiewicz.michal.carservicemanagement.entities.Car;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String firstname;

    private String lastname;

    private String email;

    private List<Car> car;

    private String city;

    private UUID addressId;

    private String userType;
}
