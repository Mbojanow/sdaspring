package pl.sdacademy.wiosnademo.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "reservations")
public class Reservation {

  @Id
  @GeneratedValue
  private Long id;

  @NotNull
  @Column(name = "surname")
  private String surname;

  @Future
  @NotNull
  @Column(name = "from")
  private Date from;

  @NotNull
  @Column(name = "to")
  private Date to;

  @NotNull
  @Column(name = "car_plate")
  private String carPlate;

  @ManyToOne
  @JoinColumn(name = "p_id")
  private ParkingLot parkingLot;

  @JsonIgnore
  @AssertTrue
  public boolean isToValid() {
    if (to == null) {
      return false;
    }
    return to.after(from);
  }
}
