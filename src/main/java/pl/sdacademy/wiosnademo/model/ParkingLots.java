package pl.sdacademy.wiosnademo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sdacademy.wiosnademo.domain.ParkingLot;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingLots {
    private List<ParkingLot> parkingLots;
}
