package com.hugopaiva.cartest.controller;

import com.hugopaiva.cartest.exception.ResourceNotFoundException;
import com.hugopaiva.cartest.model.Car;
import com.hugopaiva.cartest.services.CarManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CarController {

    @Autowired
    private CarManagerService carManagerService;

    @GetMapping(path = "/cars")
    public List<Car> getCars() {
        return carManagerService.getAllCars();
    }

    @GetMapping(path = "/cars/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable(value = "id") Long carId) throws ResourceNotFoundException {
        Car foundCar = carManagerService.getCarDetails(carId).orElseThrow(() -> new ResourceNotFoundException("Car with id " + carId + "not found!"));
        return ResponseEntity.ok().body(foundCar);
    }


    @PostMapping(path = "/cars")
    public ResponseEntity<Car> createCar(@RequestBody Car newCar) {
        return new ResponseEntity<Car>(carManagerService.save(newCar), HttpStatus.CREATED);
    }
}
