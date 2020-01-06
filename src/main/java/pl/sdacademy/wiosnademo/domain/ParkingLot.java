package pl.sdacademy.wiosnademo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity(name = "parking_lots")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ParkingLot {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = "parking name is mandatory")
    @Length(min = 5, message = "parking name too short")
    @Column(name = "parking_name")
    private String name;

    @NotNull(message = "address is mandatory")
    @Column(name = "address")
    private String address;

    @Min(value = 1, message = "Parking must have positive number of places")
    @Column(name = "places")
    private Long places;

    @OneToOne
    @JoinColumn(name = "details_id")
    private ParkingDetails details;

    @JsonIgnore
    @OneToMany(mappedBy = "parkingLot")
    private List<Reservation> reservations;

    @JsonIgnore
    @AssertTrue(message = "address has to include street name and number")
    public boolean isAddressCorrect() {
        final String[] splitAddress = address.split(" ");
        if (splitAddress.length <= 1) {
            return false;
        }

        return splitAddress[splitAddress.length - 1].chars()
                .allMatch(Character::isDigit);
    }
}
