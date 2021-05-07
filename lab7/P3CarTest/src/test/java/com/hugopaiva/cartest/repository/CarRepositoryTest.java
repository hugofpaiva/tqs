package com.hugopaiva.cartest.repository;

import com.hugopaiva.cartest.model.Car;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CarRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository carRepository;

    @Test
    public void testCarValidId() {
        Car tesla3 = new Car("Tesla", "Model 3");
        entityManager.persistAndFlush(tesla3);

        Car carFromDb = carRepository.findById(tesla3.getCar_id()).orElse(null);
        assertThat(carFromDb).isNotNull();
        assertThat(carFromDb.getModel()).isEqualTo( tesla3.getModel());
    }

    @Test
    public void testCarInvalidId() {
        Car carFromDb = carRepository.findById(-111L).orElse(null);
        assertThat(carFromDb).isNull();
    }

    @Test
    public void testAllCars() {
        Car tesla3 = new Car("Tesla", "Model 3");
        Car teslas = new Car("Tesla", "Model S");
        Car teslax = new Car("Tesla", "Model X");

        entityManager.persist(tesla3);
        entityManager.persist(teslas);
        entityManager.persist(teslax);
        entityManager.flush();

        List<Car> allCars = carRepository.findAll();

        assertThat(allCars).hasSize(3).extracting(Car::getModel).containsOnly(tesla3.getModel(), teslas.getModel(), teslax.getModel());
    }

}