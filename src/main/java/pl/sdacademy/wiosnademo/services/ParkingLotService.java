package pl.sdacademy.wiosnademo.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.sdacademy.wiosnademo.domain.ParkingLot;
import pl.sdacademy.wiosnademo.repositories.ParkingLotRepository;

@Transactional
@Service
public class ParkingLotService {

  private final ParkingLotRepository parkingLotRepository;

  public ParkingLotService(final ParkingLotRepository parkingLotRepository) {
    this.parkingLotRepository = parkingLotRepository;
  }

  public ParkingLot getById(final Long id) {
    return parkingLotRepository.findById(id)
        .orElseThrow(() -> new ParkingLotException("Parking lot with id " + id + " not found", 404));
  }

  public List<ParkingLot> getAll() {
    return parkingLotRepository.findAll();
  }

  public ParkingLot createParking(final ParkingLot toCreate) {
    validateParkingWithNameDoesNotExist(toCreate.getName());
    toCreate.setId(null);
    return parkingLotRepository.create(toCreate);
  }

  private void validateParkingWithNameDoesNotExist(final String parkingName) {
    final ParkingLot existingParking = parkingLotRepository.findByName(parkingName);
    if (existingParking != null) {
      throw new ParkingLotException("Parking with name " + parkingName + " already exists");
    }
  }

  public ParkingLot update(final Long id, final ParkingLot toUpdate) {
    final ParkingLot existingParking = getById(id);
    if (!toUpdate.getName().equals(existingParking.getName())) {
      validateParkingWithNameDoesNotExist(toUpdate.getName());
    }

    existingParking.setName(toUpdate.getName());
    existingParking.setPlaces(toUpdate.getPlaces());
    existingParking.setAddress(toUpdate.getAddress());
    return parkingLotRepository.update(existingParking);
  }

  public void deleteParking(final Long id) {
    getById(id);
    parkingLotRepository.delete(id);
  }

}
