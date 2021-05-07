package com.hugopaiva.cartest.controller;

import com.hugopaiva.cartest.JsonUtil;
import com.hugopaiva.cartest.model.Car;
import com.hugopaiva.cartest.services.CarManagerService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@WebMvcTest(CarController.class)
public class CarControllerTestRestAssuredMockMvc {

    @Autowired
    private MockMvc mvcClient;

    @MockBean
    private CarManagerService carManagerService;

    @BeforeEach
    public void associateAssured() {
        RestAssuredMockMvc.mockMvc(mvcClient);
    }

    @Test
    public void testPostCar() throws Exception {
        Car tesla3 = new Car("Tesla", "Model 3");
        tesla3.setCar_id(1L);

        when(carManagerService.save(Mockito.any())).thenReturn(tesla3);

        RestAssuredMockMvc.given().header("Content-Type", "application/json").body(JsonUtil.toJson(tesla3))
                .post("/api/cars")
                .then().assertThat().statusCode(201)
                .and().body("maker", equalTo("Tesla"))
                .and().body("model", equalTo("Model 3"));

        verify(carManagerService, times(1)).save(tesla3);
    }

    @Test
    public void testGetCarIdValid() throws Exception {
        Car newCar = new Car("Tesla", "Model 3");
        newCar.setCar_id(1L);

        when(carManagerService.getCarDetails(newCar.getCar_id())).thenReturn(Optional.of(newCar));

        RestAssuredMockMvc.given().when()
                .get("/api/cars/1")
                .then().assertThat().statusCode(200)
                .and().body("maker", equalTo("Tesla"))
                .and().body("model", equalTo("Model 3"));

        verify(carManagerService, times(1)).getCarDetails(newCar.getCar_id());
    }

    @Test
    public void testGetCarIdInvalid() throws Exception{
        RestAssuredMockMvc.given().when()
                .get("/api/cars/1")
                .then().assertThat().statusCode(404);

        verify(carManagerService, times(1)).getCarDetails(1L);

    }

    @Test
    public void testGetAllCars() throws Exception {
        Car tesla3 = new Car("Tesla", "Model 3");
        Car teslas = new Car("Tesla", "Model S");
        Car teslax = new Car("Tesla", "Model X");

        List<Car> allCars = Arrays.asList(tesla3, teslas, teslax);

        given(carManagerService.getAllCars()).willReturn(allCars);

        RestAssuredMockMvc.given().when()
                .get("/api/cars")
                .then().assertThat().statusCode(200)
                .and().body("", hasSize(3))
                .and().body("maker[0]", is(tesla3.getMaker()))
                .and().body("model[0]", is(tesla3.getModel()))
                .and().body("maker[1]", is(teslas.getMaker()))
                .and().body("model[1]", is(teslas.getModel()))
                .and().body("maker[2]", is(teslax.getMaker()))
                .and().body("model[2]", is(teslax.getModel()));

        verify(carManagerService, times(1)).getAllCars();

    }

}
