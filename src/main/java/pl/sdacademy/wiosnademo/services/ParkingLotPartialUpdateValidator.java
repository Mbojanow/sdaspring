package pl.sdacademy.wiosnademo.services;

import static java.util.Objects.nonNull;

import org.springframework.stereotype.Component;

import pl.sdacademy.wiosnademo.domain.ParkingLot;
import pl.sdacademy.wiosnademo.exceptions.ParkingLotGenericException;
import pl.sdacademy.wiosnademo.repositories.ParkingLotRepository;

@Component
public class ParkingLotPartialUpdateValidator {

  private final ParkingLotRepository parkingLotRepository;
  private final ParkingLotCommonValidator parkingLotCommonValidator;

  public ParkingLotPartialUpdateValidator(final ParkingLotRepository parkingLotRepository,
                                          final ParkingLotCommonValidator parkingLotCommonValidator) {
    this.parkingLotRepository = parkingLotRepository;
    this.parkingLotCommonValidator = parkingLotCommonValidator;
  }

  public ParkingLot validate(final Long id, final ParkingLot parkingLot) {
    final ParkingLot existingParking = parkingLotCommonValidator.findExistingById(id);
    if (nonNull(parkingLot.getName())) {
      // validateParking name wyrzucic do commonValidator
      validateParkingName(parkingLot.getName());
      parkingLotCommonValidator.validateParkingName(existingParking, parkingLot);
    }

    if (nonNull(parkingLot.getPlaces())) {
      validatePlaces(parkingLot.getPlaces());
    }

    if (nonNull(parkingLot.getAddress())) {
      validateAddress(parkingLot);
    }
    return existingParking;
  }

  private void validateParkingName(final String parkingName) {
    if (parkingName.length() < 5) {
      // mozna wyrzucic string do stałej
      throw new ParkingLotGenericException("Name length has to be at least 5");
    }
  }

  private void validatePlaces(final Long places) {
    if (places <= 0) {
      throw new ParkingLotGenericException("Places number has to be positive");
    }
  }

  private void validateAddress(final ParkingLot parkingLot) {
    if (!parkingLot.isAddressValid()) {
      throw new ParkingLotGenericException("Address is invalid");
    }
  }
}
