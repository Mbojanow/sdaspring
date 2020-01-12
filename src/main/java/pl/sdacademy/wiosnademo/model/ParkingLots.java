package pl.sdacademy.wiosnademo.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sdacademy.wiosnademo.domain.ParkingLot;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingLots {
  private List<ParkingLot> parkingLots;
}
