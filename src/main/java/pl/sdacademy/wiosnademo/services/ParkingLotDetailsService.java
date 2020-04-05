package pl.sdacademy.wiosnademo.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.sdacademy.wiosnademo.domain.ParkingDetails;
import pl.sdacademy.wiosnademo.domain.ParkingLot;
import pl.sdacademy.wiosnademo.repositories.ParkingDetailsRepository;
import pl.sdacademy.wiosnademo.repositories.ParkingLotRepository;

@Service
@Transactional
public class ParkingLotDetailsService {

  private final ParkingDetailsRepository parkingDetailsRepository;
  private final ParkingLotRepository parkingLotRepository;

  public ParkingLotDetailsService(final ParkingDetailsRepository parkingDetailsRepository,
                                  final ParkingLotRepository parkingLotRepository) {
    this.parkingDetailsRepository = parkingDetailsRepository;
    this.parkingLotRepository = parkingLotRepository;
  }

  public ParkingLot addDetailsToParking(final Long parkingId, final ParkingDetails parkingDetails) {
    final ParkingLot parkingLot = parkingLotRepository.findById(parkingId)
        .orElseThrow(() -> new ParkingLotException("Cannot add details to parking that does not exist"));

    if (parkingLot.getParkingDetails() != null) {
      throw new ParkingLotException("Parking already has details attached");
    }

    parkingDetailsRepository.findByPhoneNumber(parkingDetails.getPhoneNumber())
        .ifPresent(details -> { throw new ParkingLotException("Details with given phone already exist"); });

    final ParkingDetails savedParkingDetails = parkingDetailsRepository.save(parkingDetails);
    parkingLot.setParkingDetails(savedParkingDetails);
    parkingLotRepository.update(parkingLot);
    return parkingLot;
  }
}
