package pl.sdacademy.wiosnademo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.stream.Stream;

import static java.util.Objects.isNull;

@Entity
@Table(name = "parking_details")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ParkingDetails {

    private static final int MOBILE_PHONE_EXPECTED_PARTS_NUM = 3;

    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = "mobile phone has to be provided")
    @Column(name = "mobile_phone")
    private String mobilePhone;

    @NotNull(message = "manager name is mandatory")
    @Length(min = 2)
    @Column(name = "manager_name")
    private String managerName;

    @Null(message = "cannot provide parking lot for parking details here")
    @OneToOne
    @JoinColumn(name = "parking_id")
    private ParkingLot parkingLot;

    @AssertTrue(message = "mobile phone validation failed")
    public boolean isMobileValid() {
        if (isNull(mobilePhone)) {
            return false;
        }

        final String[] splitData = mobilePhone.split("-");
        if (splitData.length != MOBILE_PHONE_EXPECTED_PARTS_NUM) {
            return false;
        }
        return Stream.of(splitData).allMatch(x -> x.matches("[0-9]{3}"));
    }
}
