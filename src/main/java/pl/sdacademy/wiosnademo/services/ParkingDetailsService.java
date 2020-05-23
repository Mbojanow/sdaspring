package pl.sdacademy.wiosnademo.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.sdacademy.wiosnademo.domain.ParkingDetails;
import pl.sdacademy.wiosnademo.domain.ParkingLot;
import pl.sdacademy.wiosnademo.exceptions.SdaException;
import pl.sdacademy.wiosnademo.repositories.ParkingDetailsRepository;
import pl.sdacademy.wiosnademo.repositories.ParkingLotRepository;

@Service
@Transactional
public class ParkingDetailsService {

  private final ParkingDetailsRepository parkingDetailsRepository;
  private final ParkingLotRepository parkingLotRepository;
  private final ParkingLotService parkingLotService;

  public ParkingDetailsService(final ParkingDetailsRepository parkingDetailsRepository,
                               final ParkingLotRepository parkingLotRepository, final ParkingLotService parkingLotService) {
    this.parkingDetailsRepository = parkingDetailsRepository;
    this.parkingLotRepository = parkingLotRepository;
    this.parkingLotService = parkingLotService;
  }

  public ParkingLot addDetailsToParking(final ParkingDetails parkingDetails, final Long parkingId) {
    final ParkingLot existingParking = getValidParking(parkingDetails, parkingId);
    parkingDetailsRepository.save(parkingDetails);
    existingParking.setParkingDetails(parkingDetails);
    parkingLotRepository.update(existingParking);
    return existingParking;
  }

  private ParkingLot getValidParking(final ParkingDetails parkingDetails, final Long parkingId) {
    final ParkingLot existingParking = parkingLotService.findParkingById(parkingId);
    if (existingParking.getParkingDetails() != null) { // 1-1 eager
      throw new SdaException("Cannot create details for parking because they are already assigned");
    }
    parkingDetailsRepository.findByPhoneNumber(parkingDetails.getPhoneNumber())
        .ifPresent(details -> {
          throw new SdaException("Parking with given phone already exists");
        });
    return existingParking;
  }
}
