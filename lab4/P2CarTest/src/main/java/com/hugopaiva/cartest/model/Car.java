package com.hugopaiva.cartest.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Data
@EqualsAndHashCode
@Entity
public class Car {

    @Id
    @GeneratedValue
    private Long carId;

    private String maker;

    private String model;

    public Car() {

    }

    public Car(String maker, String model) {
        this.maker = maker;
        this.model = model;
    }

}
