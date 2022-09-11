package dat3.cars.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.cars.entity.Car;
import dat3.cars.entity.Member;
import dat3.cars.entity.Reservation;

import java.time.LocalDate;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationResponse {
    int id;
    String username;
    int carId;


    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
    LocalDateTime reservationDate;
    @JsonFormat(pattern = "dd-MM-yyyy",shape = JsonFormat.Shape.STRING)
    String rentalDate;

    public ReservationResponse(Reservation r){
        this.id = r.getId();
        this.reservationDate = r.getReservationDate();
        this.rentalDate = r.getRentalDate();
        this.username = r.getMember().getUsername();
        this.carId = r.getCar().getId();
    }



}
