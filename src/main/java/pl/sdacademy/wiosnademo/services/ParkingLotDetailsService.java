package pl.sdacademy.wiosnademo.services;

import static java.util.Objects.nonNull;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.sdacademy.wiosnademo.domain.ParkingLot;
import pl.sdacademy.wiosnademo.domain.ParkingLotDetails;
import pl.sdacademy.wiosnademo.exceptions.ParkingLotGenericException;
import pl.sdacademy.wiosnademo.repositories.ParkingLotDetailsRepository;

@Service
@Transactional
public class ParkingLotDetailsService {

  private final ParkingLotDetailsRepository parkingLotDetailsRepository;
  private final ParkingLotService parkingLotService;

  public ParkingLotDetailsService(final ParkingLotDetailsRepository parkingLotDetailsRepository,
                                  final ParkingLotService parkingLotService) {
    this.parkingLotDetailsRepository = parkingLotDetailsRepository;
    this.parkingLotService = parkingLotService;
  }

  public ParkingLot addDetails(final Long parkingId, final ParkingLotDetails parkingLotDetails) {
    final ParkingLot parkingLot = parkingLotService.findById(parkingId);
    if (nonNull(parkingLot.getDetails())) {
      throw new ParkingLotGenericException("Parking lot details already assigned");
    }
    parkingLotDetailsRepository.findByMobileNumber(parkingLotDetails.getMobileNumber())
        .ifPresent(details -> {
          throw new ParkingLotGenericException("Mobile phone number already assigned to another parking");
        });
    // TEN SAVE WYMAGANY W PRZYPADKU BRAKU CASCADY
    //final ParkingLotDetails savedParkingLotDetails = parkingLotDetailsRepository.save(parkingLotDetails);
    parkingLot.setDetails(parkingLotDetails);
    parkingLotDetails.setParkingLot(parkingLot);
    return parkingLotService.update(parkingId, parkingLot);
  }
}
