package com.hugopaiva.cartest.controller;

import com.hugopaiva.cartest.model.Car;
import com.hugopaiva.cartest.repository.CarRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "application-integrationtest.properties")
public class CarControllerTestITRealDB {

    @LocalServerPort
    int randomServerPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CarRepository repository;

    @AfterEach
    public void resetDb() {
        repository.deleteAll();
    }


    @Test
    public void testPostCar() {
        Car tesla3 = new Car("Tesla", "Model 3");
        tesla3.setCarId(1L);
        ResponseEntity<Car> entity = restTemplate.postForEntity("/api/cars", tesla3, Car.class);

        List<Car> found = repository.findAll();
        assertThat(found).extracting(Car::getModel).containsOnly("Model 3");
    }

    @Test
    public void testGetCarIdValid() {
        Car newCar = new Car("Tesla", "Model 3");
        repository.saveAndFlush(newCar);

        ResponseEntity<Car> response = restTemplate
                .exchange("/api/cars/"+newCar.getCarId(), HttpMethod.GET, null, new ParameterizedTypeReference<Car>() {
                });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).extracting(Car::getModel).isEqualTo("Model 3");

    }

    @Test
    public void testGetCarIdInvalid() {
        ResponseEntity<Car> response = restTemplate
                .exchange("/api/cars/1", HttpMethod.GET, null, new ParameterizedTypeReference<Car>() {
                });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void testGetAllCars() {
        createTestCar("Tesla", "Model 3");
        createTestCar("Tesla", "Model S");
        createTestCar("Tesla", "Model X");


        ResponseEntity<List<Car>> response = restTemplate
                .exchange("/api/cars", HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>() {
                });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).extracting(Car::getModel).containsExactly("Model 3", "Model S", "Model X");

    }


    private void createTestCar(String maker, String model) {
        Car car = new Car(maker, model);
        repository.saveAndFlush(car);
    }

}
