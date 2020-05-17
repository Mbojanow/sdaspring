package pl.sdacademy.wiosnademo.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.sdacademy.wiosnademo.domain.ParkingLot;
import pl.sdacademy.wiosnademo.exceptions.SdaException;
import pl.sdacademy.wiosnademo.repositories.ParkingLotRepository;

@Service
@Transactional
public class ParkingLotService {

  private final ParkingLotRepository parkingLotRepository;

  public ParkingLotService(final ParkingLotRepository parkingLotRepository) {
    this.parkingLotRepository = parkingLotRepository;
  }

  public ParkingLot createParkingLot(final ParkingLot parkingLot) {
    validateParkingNameNotExist(parkingLot.getName());
    return parkingLotRepository.create(parkingLot);
  }

  private void validateParkingNameNotExist(final String parkingName) {
    parkingLotRepository.findByName(parkingName)
        .ifPresent(pl -> {
          throw new SdaException("Parking with name " + parkingName + " already exists");
        });
  }

  public List<ParkingLot> findAllParkings() {
    return parkingLotRepository.getAll();
  }

  public ParkingLot findParkingById(final Long id) {
    return parkingLotRepository.findById(id)
        .orElseThrow(() -> new SdaException("Parking with id " + id + " does not exist"));
  }

  public ParkingLot updateParking(final Long id, final ParkingLot parkingLot) {
    final ParkingLot existingParking = findParkingById(id);
    if (!existingParking.getName().equals(parkingLot.getName())) {
      validateParkingNameNotExist(parkingLot.getName());
    }

    // MUSIMY PRACOWAC na existingParking bo o referencji parkingLot hibernate nie ma pojecia -> detached entity! -> boom
    existingParking.setAddress(parkingLot.getAddress());
    existingParking.setName(parkingLot.getName());
    existingParking.setPlaces(parkingLot.getPlaces());
    return parkingLotRepository.update(existingParking);
  }

  public void deleteById(final Long id) {
    findParkingById(id);
    parkingLotRepository.delete(id);
  }

  public ParkingLot getParkingByName(final String name) {
    return parkingLotRepository.findByName(name).orElse(null);
  }
}
