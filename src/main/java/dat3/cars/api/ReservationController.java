package dat3.cars.api;

import dat3.cars.dto.ReservationResponse;
import dat3.cars.entity.Reservation;
import dat3.cars.repository.CarRepository;
import dat3.cars.repository.MemberRepository;
import dat3.cars.service.ReservationService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;

@CrossOrigin
@RestController
@RequestMapping("api/reservations")
public class ReservationController {
    ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("{username}/{carId}/{date}")
    public ReservationResponse makeReservation(@PathVariable("username") String username,
                                               @PathVariable("carId") int carId,
                                               @PathVariable("date") String date){
        reservationService.reserveCar(username,carId,date);
        Reservation reservation = reservationService.getReservation(reservationService.getReservationId());
        return new ReservationResponse(reservation);

                /*new Reservation(memberRepository.findById(username).orElseThrow(
                ()->  new ResponseStatusException(HttpStatus.BAD_REQUEST,"Member can't be found")),carRepository.findById(carId).orElseThrow(
                ()->  new ResponseStatusException(HttpStatus.BAD_REQUEST,"Car can't be found")),date);*/

    }
}
