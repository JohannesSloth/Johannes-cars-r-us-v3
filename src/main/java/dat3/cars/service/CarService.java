package dat3.cars.service;

import dat3.cars.dto.CarResponse;
import dat3.cars.dto.MemberResponse;
import dat3.cars.entity.Car;
import dat3.cars.entity.Member;
import dat3.cars.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {
    private CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }
    public List<CarResponse> getCars() {
        List<Car> cars = carRepository.findAll();
        List<CarResponse> response = cars.stream().map(car -> new CarResponse(car,false)).collect(Collectors.toList());
        return response;
    }
    public void deleteById(int id) {
        carRepository.deleteById(id);
    }
}
