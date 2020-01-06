package pl.sdacademy.wiosnademo.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Table
@Entity(name = "reservations")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Reservation {

    @Id
    @GeneratedValue
    private Long id;

    @JsonProperty("surname")
    @NotNull(message = "surname is mandatory")
    @Length(min = 2)
    @Column(name = "surname")
    private String bookingPersonSurname;

    @NotNull(message = "from field has to be provided")
    @Future(message = "reservation start has to be in the future")
    @Column(name = "from_timestamp")
    private Date from;

    @NotNull(message = "to field has to be provided")
    @Future(message = "reservation end time has to be in the future")
    @Column(name = "to_timestamp")
    private Date to;

    @NotNull(message = "car plate is a mandatory field")
    @Length(min = 6, max = 8, message = "cap plate length has to be between 6 and 8")
    @Column(name = "car_plate")
    private String carPlate;

    @JsonIgnore
    @AssertTrue(message = "can only reserve for full hours")
    public boolean isReservationPeriodValid() {
        return to.after(from) && ((to.getTime() - from.getTime()) % 3600000 == 0);
    }

    @ManyToOne
    @JoinColumn(name = "parking_id")
    private ParkingLot parkingLot;
}
