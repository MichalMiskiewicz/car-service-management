package com.miskiewicz.michal.carservicemanagement.entities;

import com.miskiewicz.michal.carservicemanagement.entities.enums.UserType;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends DefaultEntity {

    private String firstname;

    private String lastname;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @ManyToOne
    private Address address;

    @OneToMany
    private List<Car> car;
}
