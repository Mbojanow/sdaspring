package pl.sdacademy.wiosnademo.services;

import static java.util.Objects.nonNull;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.sdacademy.wiosnademo.domain.ParkingLot;
import pl.sdacademy.wiosnademo.exceptions.ParkingLotGenericException;
import pl.sdacademy.wiosnademo.exceptions.ParkingLotNotFoundException;
import pl.sdacademy.wiosnademo.repositories.ParkingLotRepository;

@Service
@Transactional
public class ParkingLotService {

  private final ParkingLotRepository parkingLotRepository;
  private final ParkingLotCommonValidator parkingLotCommonValidator;
  private final ParkingLotPartialUpdateValidator parkingLotPartialUpdateValidator;

  public ParkingLotService(final ParkingLotRepository parkingLotRepository,
                           final ParkingLotCommonValidator parkingLotCommonValidator,
                           final ParkingLotPartialUpdateValidator parkingLotPartialUpdateValidator) {
    this.parkingLotRepository = parkingLotRepository;
    this.parkingLotCommonValidator = parkingLotCommonValidator;
    this.parkingLotPartialUpdateValidator = parkingLotPartialUpdateValidator;
  }

  public ParkingLot create(final ParkingLot parkingLot) {
    throwIfParkingWithGivenNameExists(parkingLot.getName());
    return parkingLotRepository.create(parkingLot);
  }

  private void throwIfParkingWithGivenNameExists(final String name) {
    parkingLotRepository.findByName(name)
        .ifPresent(pl -> { throw new ParkingLotGenericException("Parking lot with name "
            + name + " already exists");});
  }

  public ParkingLot update(final Long id, final ParkingLot givenParkingLot) {
    parkingLotCommonValidator.validate(id, givenParkingLot);
    givenParkingLot.setId(id);
    return parkingLotRepository.update(givenParkingLot);
  }

  private ParkingLot findExistingById(final Long id) {
    return parkingLotRepository.findById(id).orElseThrow(() ->
        new ParkingLotNotFoundException("Cannot update non existing parking lot"));
  }

  public ParkingLot findById(final Long id) {
    return findExistingById(id);
  }

  public List<ParkingLot> getAll() {
    return parkingLotRepository.getAll();
  }

  public void deleteById(final Long id) {
    findExistingById(id);
    parkingLotRepository.delete(id);
  }

  public ParkingLot updatePartially(final Long id, final ParkingLot updatedParkingLotProperties) {
    final ParkingLot existingParkingLot = parkingLotPartialUpdateValidator
        .validate(id, updatedParkingLotProperties);
    if (nonNull(updatedParkingLotProperties.getName())) {
      existingParkingLot.setName(updatedParkingLotProperties.getName());
    }

    if (nonNull(updatedParkingLotProperties.getAddress())) {
      existingParkingLot.setAddress(updatedParkingLotProperties.getAddress());
    }

    if (nonNull(updatedParkingLotProperties.getPlaces())) {
      existingParkingLot.setPlaces(updatedParkingLotProperties.getPlaces());
    }
    return parkingLotRepository.update(existingParkingLot);
  }
}
