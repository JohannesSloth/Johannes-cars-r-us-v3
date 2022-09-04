package dat3.cars.service;

import dat3.cars.dto.CarRequest;
import dat3.cars.dto.CarResponse;
import dat3.cars.entity.Car;
import dat3.cars.repository.CarRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class CarServiceMockWithH2Test {
    public CarService carService;
    public static CarRepository carRepository;

    static int car1Id, car2Id;

    @BeforeAll
    public static void initTestData(@Autowired CarRepository car_Repository){
        carRepository = car_Repository;
        carRepository.deleteAll();
        Car car1 = new Car("Honda","Civic", 600, 50);
        Car car2 = new Car("Lada", "5000", 300, 20);
        car1 = carRepository.save(car1);
        car2 = carRepository.save(car2);
        car1Id = car1.getId();
        car2Id = car2.getId();
        carRepository = car_Repository;
    }
    @BeforeEach
    public void initCarService() {
        carService = new CarService(carRepository);
    }
    @Test
    void addCar() {
        CarRequest request = new CarRequest("Mercedes", "Benz", 700, 200);
        CarResponse response = carService.addCar(request);
        assertTrue(response.getId() > 0);
        //assertTrue(response.getCreated().isBefore(LocalDateTime.now()));
    }
    @Test
    void getCars() throws Exception {
        List<CarResponse> cars = carService.getCars();
        assertEquals(2, cars.size());
        //assertNull(cars.get(0).getBestDiscount());
    }
}
