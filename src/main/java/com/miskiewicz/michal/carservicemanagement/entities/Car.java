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
public class Car extends DefaultEntity {

    private String type;

    private String brand;

    private String model;

    private String registrationNumber;

    private String VinNumber;
}
