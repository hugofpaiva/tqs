package com.hugopaiva.cartest.services;

import com.hugopaiva.cartest.model.Car;
import com.hugopaiva.cartest.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;


@ExtendWith(MockitoExtension.class)
class CarManagerServiceTest {

    // lenient is required because we load some expectations in the setup
    // that are not used in all the tests. As an alternative, the expectations
    // could move into each test method and be trimmed: no need for lenient
    @Mock( lenient = true)
    private CarRepository carRepository;

    @InjectMocks
    private CarManagerService carManagerService;

    @BeforeEach
    public void setUp() {
        Car tesla3 = new Car("Tesla", "Model 3");
        tesla3.setCar_id(1L);
        Car teslas = new Car("Tesla", "Model S");
        teslas.setCar_id(2L);
        Car teslax = new Car("Tesla", "Model X");
        teslax.setCar_id(3L);

        List<Car> allCars = Arrays.asList(tesla3, teslas, teslax);

        Mockito.when(carRepository.findAll()).thenReturn(allCars);
        Mockito.when(carRepository.findById(tesla3.getCar_id())).thenReturn(Optional.of(tesla3));
        Mockito.when(carRepository.findById(teslas.getCar_id())).thenReturn(Optional.of(teslas));
        Mockito.when(carRepository.findById(teslax.getCar_id())).thenReturn(Optional.of(teslax));
        Mockito.when(carRepository.findById(99L)).thenReturn(Optional.empty());
    }

    @Test
    public void testCarValidId() {
        Optional<Car> carFromDb = carManagerService.getCarDetails(1L);

        assertThat(carFromDb.isPresent()).isTrue();

        Car carFromOptional = carFromDb.get();

        assertThat(carFromOptional.getModel()).isEqualTo("Model 3");

        verifyFindByCarIdIsCalledOnce();
    }

    @Test
    public void testCarInvalidId() {
        Optional<Car> carFromDb = carManagerService.getCarDetails(99L);

        assertThat(carFromDb.isEmpty()).isTrue();

        verifyFindByCarIdIsCalledOnce();
    }

    @Test
    public void testAllCars() {
        Car tesla3 = new Car("Tesla", "Model 3");
        tesla3.setCar_id(1L);
        Car teslas = new Car("Tesla", "Model S");
        teslas.setCar_id(2L);
        Car teslax = new Car("Tesla", "Model X");
        teslax.setCar_id(3L);

        List<Car> allCars = carManagerService.getAllCars();

        verifyFindAllCarsIsCalledOnce();
        assertThat(allCars).hasSize(3).extracting(Car::getModel).contains(tesla3.getModel(), teslas.getModel(), teslax.getModel());
    }

    private void verifyFindByCarIdIsCalledOnce() {
        Mockito.verify(carRepository, times(1)).findById(Mockito.anyLong());
    }

    private void verifyFindAllCarsIsCalledOnce() {
        Mockito.verify(carRepository, times(1)).findAll();
    }


}