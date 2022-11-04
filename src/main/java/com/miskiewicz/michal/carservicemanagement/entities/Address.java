package com.miskiewicz.michal.carservicemanagement.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address extends DefaultEntity {

    private String street;

    private String houseNumber;

    private String zipCode;

    private String city;
}
