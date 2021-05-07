package com.hugopaiva.cartest.controller;

import com.hugopaiva.cartest.JsonUtil;
import com.hugopaiva.cartest.model.Car;
import com.hugopaiva.cartest.repository.CarRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import static io.restassured.RestAssured.given;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class CarControllerTestTestContainersRestAssuredIT {

    @LocalServerPort
    int randomServerPort;

    @Autowired
    private CarRepository repository;

    @Container
    public static PostgreSQLContainer container = new PostgreSQLContainer("postgres:12")
            .withUsername("duke")
            .withPassword("password")
            .withDatabaseName("test");

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.username", container::getUsername);
    }

    @AfterEach
    public void resetDb() {
        repository.deleteAll();
    }

    @Test
    public void testPostCar() throws IOException {
        Car tesla3 = new Car("Tesla", "Model 3");
        repository.saveAndFlush(tesla3);

        given().header("Content-Type", "application/json").body(JsonUtil.toJson(tesla3))
                .post(getBaseUrl()+"/api/cars")
                .then().assertThat().statusCode(201)
                .and().body("maker", equalTo("Tesla"))
                .and().body("model", equalTo("Model 3"));
    }

    @Test
    public void testGetCarIdValid() {
        Car newCar = new Car("Tesla", "Model 3");
        repository.saveAndFlush(newCar);

        given().when()
                .get(getBaseUrl()+"/api/cars/"+newCar.getCar_id().intValue())
                .then().assertThat().statusCode(200)
                .and().body("maker", equalTo("Tesla"))
                .and().body("model", equalTo("Model 3"));

    }

    @Test
    public void testGetCarIdInvalid() {
        given().when()
                .get(getBaseUrl()+"/api/cars/222")
                .then().assertThat().statusCode(404);
    }

    @Test
    public void testGetAllCars() {
        createTestCar("Tesla", "Model 3");
        createTestCar("Tesla", "Model S");
        createTestCar("Tesla", "Model X");


        given().when()
                .get(getBaseUrl()+"/api/cars")
                .then().assertThat().statusCode(200)
                .and().body("", hasSize(3))
                .and().body("maker[0]", is("Tesla"))
                .and().body("model[0]", is("Model 3"))
                .and().body("maker[1]", is("Tesla"))
                .and().body("model[1]", is("Model S"))
                .and().body("maker[2]", is("Tesla"))
                .and().body("model[2]", is("Model X"));

    }


    private void createTestCar(String maker, String model) {
        Car car = new Car(maker, model);
        repository.saveAndFlush(car);
    }

    public String getBaseUrl() {
        return "http://localhost:"+randomServerPort;

    }

}
