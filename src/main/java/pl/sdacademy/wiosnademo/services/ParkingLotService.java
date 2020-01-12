package pl.sdacademy.wiosnademo.services;

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

  public ParkingLotService(final ParkingLotRepository parkingLotRepository) {
    this.parkingLotRepository = parkingLotRepository;
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
    final ParkingLot parkingLotToUpdate = findExistingById(id);
    if (!parkingLotToUpdate.getName().equals(givenParkingLot.getName())) {
      throwIfParkingWithGivenNameExists(givenParkingLot.getName());
    }
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
}
