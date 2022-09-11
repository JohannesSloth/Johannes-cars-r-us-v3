package dat3.cars.dto;

import dat3.cars.entity.Car;
import dat3.cars.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequest {
    String username;
    int carId;
    String rentalDate;
}
