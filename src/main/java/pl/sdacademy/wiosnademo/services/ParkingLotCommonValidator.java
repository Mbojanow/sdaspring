package pl.sdacademy.wiosnademo.services;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import pl.sdacademy.wiosnademo.domain.ParkingLot;
import pl.sdacademy.wiosnademo.exceptions.ParkingLotGenericException;
import pl.sdacademy.wiosnademo.exceptions.ParkingLotNotFoundException;
import pl.sdacademy.wiosnademo.repositories.ParkingLotRepository;

@Component
@Transactional
public class ParkingLotCommonValidator {

  private final ParkingLotRepository parkingLotRepository;

  public ParkingLotCommonValidator(final ParkingLotRepository parkingLotRepository) {
    this.parkingLotRepository = parkingLotRepository;
  }

  public void validate(final Long id, final ParkingLot givenParkingLot) {
    final ParkingLot parkingLotToUpdate = findExistingById(id);
    validateParkingName(givenParkingLot, parkingLotToUpdate);
  }

  public void validateParkingName(final ParkingLot givenParkingLot,
                                  final ParkingLot parkingLotToUpdate) {
    if (!parkingLotToUpdate.getName().equals(givenParkingLot.getName())) {
      throwIfParkingWithGivenNameExists(parkingLotToUpdate.getName());
    }
  }

  public ParkingLot findExistingById(final Long id) {
    return parkingLotRepository.findById(id).orElseThrow(() ->
        new ParkingLotNotFoundException("Cannot update non existing parking lot"));
  }

  private void throwIfParkingWithGivenNameExists(final String name) {
    parkingLotRepository.findByName(name)
        .ifPresent(pl -> { throw new ParkingLotGenericException("Parking lot with name "
            + name + " already exists");});
  }
}
