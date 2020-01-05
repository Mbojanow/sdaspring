package pl.sdacademy.wiosnademo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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
    private String name;

    @NotNull(message = "address is mandatory")
    private String address;

    @Min(value = 1, message = "Parking must have positive number of places")
    private Long places;

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
