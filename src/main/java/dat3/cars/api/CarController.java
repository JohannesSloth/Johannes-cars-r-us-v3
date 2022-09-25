package dat3.cars.api;

import dat3.cars.dto.CarRequest;
import dat3.cars.dto.CarResponse;
import dat3.cars.dto.MemberRequest;
import dat3.cars.dto.MemberResponse;
import dat3.cars.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/cars")
public class CarController {
    CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public List<CarResponse> getCars(){
        return carService.getCars();
    }
    @GetMapping("/{id}")//(path =) ?
    public CarResponse getCarById(@PathVariable int id) throws Exception {
        CarResponse response = carService.getCarById(id);
        return response;
    }



    @PostMapping
    public CarResponse addCar (@RequestBody CarRequest body) {
        return carService.addCar(body);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Boolean> editCar(@RequestBody CarRequest body, @PathVariable int id){
        carService.editCar(body,id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteCarById(@PathVariable int id){
        carService.deleteById(id);
    }
}

