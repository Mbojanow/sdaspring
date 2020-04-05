package pl.sdacademy.wiosnademo.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.sdacademy.wiosnademo.domain.ParkingLot;
import pl.sdacademy.wiosnademo.repositories.ParkingLotRepository;

@Service
@Transactional
public class ParkingSearchService {

  private final ParkingLotRepository parkingLotRepository;

  public ParkingSearchService(final ParkingLotRepository parkingLotRepository) {
    this.parkingLotRepository = parkingLotRepository;
  }

  public ParkingLot findByName(final String name) {
    final ParkingLot parking = parkingLotRepository.findByName(name);
    if (parking == null) {
      throw new ParkingLotException("Parking does not exist", 404);
    }
    return parking;
  }
}
