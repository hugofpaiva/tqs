package com.hugopaiva.cartest.controller;

import com.hugopaiva.cartest.JsonUtil;
import com.hugopaiva.cartest.model.Car;
import com.hugopaiva.cartest.services.CarManagerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class CarControllerMockTest {

    @Autowired
    private MockMvc mvcClient;

    @MockBean
    private CarManagerService carManagerService;


    @Test
    public void testPostCar() throws Exception {
        Car tesla3 = new Car("Tesla", "Model 3");
        tesla3.setCarId(1L);

        when(carManagerService.save(Mockito.any())).thenReturn(tesla3);

        mvcClient.perform(post("/api/cars").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(tesla3)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.maker", is("Tesla")))
                .andExpect(jsonPath("$.model", is("Model 3")));

        verify(carManagerService, times(1)).save(tesla3);
    }

    @Test
    public void testGetCarIdValid() throws Exception {
        Car newCar = new Car("Tesla", "Model 3");
        newCar.setCarId(1L);

        when(carManagerService.getCarDetails(newCar.getCarId())).thenReturn(Optional.of(newCar));

        mvcClient.perform(get("/api/cars/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("maker").value("Tesla"))
                .andExpect(jsonPath("model").value("Model 3"));

        verify(carManagerService, times(1)).getCarDetails(newCar.getCarId());
    }

    @Test
    public void testGetCarIdInvalid() throws Exception{

        mvcClient.perform(get("/api/cars/1"))
                .andExpect(status().isNotFound());

        verify(carManagerService, times(1)).getCarDetails(1L);

    }

    @Test
    public void testGetAllCars() throws Exception {
        Car tesla3 = new Car("Tesla", "Model 3");
        Car teslas = new Car("Tesla", "Model S");
        Car teslax = new Car("Tesla", "Model X");

        List<Car> allCars = Arrays.asList(tesla3, teslas, teslax);

        given(carManagerService.getAllCars()).willReturn(allCars);

        mvcClient.perform(get("/api/cars").contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].maker", is(tesla3.getMaker())))
                .andExpect(jsonPath("$[0].model", is(tesla3.getModel())))
                .andExpect(jsonPath("$[1].maker", is(teslas.getMaker())))
                .andExpect(jsonPath("$[1].model", is(teslas.getModel())))
                .andExpect(jsonPath("$[2].maker", is(teslax.getMaker())))
                .andExpect(jsonPath("$[2].model", is(teslax.getModel())));


        verify(carManagerService, times(1)).getAllCars();

    }

}