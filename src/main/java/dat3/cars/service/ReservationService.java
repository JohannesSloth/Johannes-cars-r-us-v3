package dat3.cars.service;

import dat3.cars.entity.Car;
import dat3.cars.entity.Member;
import dat3.cars.entity.Reservation;
import dat3.cars.repository.CarRepository;
import dat3.cars.repository.MemberRepository;
import dat3.cars.repository.ReservationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Service
public class ReservationService {

    private ReservationRepository reservationRepository;
    private MemberRepository memberRepository;
    private CarRepository carRepository;
    private static int reservationId;

    public ReservationService(ReservationRepository reservationRepository, MemberRepository memberRepository, CarRepository carRepository) {
        this.reservationRepository = reservationRepository;
        this.memberRepository = memberRepository;
        this.carRepository = carRepository;
    }

    public void reserveCar(String username, int carId, String date){

        Member member = memberRepository.findById(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found"));
        Car car = carRepository.findById(carId).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car not found"));

        boolean exists = reservationRepository.existsByCar_IdAndRentalDate(carId, date);
        if (!exists) {
            Reservation reservation = new Reservation(member, car, date);
            reservationRepository.save(reservation);
            reservationId = reservationRepository.save(reservation).getId();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car reserved for this date");
        }
    }
    public Reservation getReservation(int id){
        return reservationRepository.findById(id).orElseThrow();
    }
    public int getReservationId(){
        return reservationId;
    }
}
