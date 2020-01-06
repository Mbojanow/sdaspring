package pl.sdacademy.wiosnademo.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sdacademy.wiosnademo.domain.ParkingDetails;
import pl.sdacademy.wiosnademo.domain.ParkingLot;
import pl.sdacademy.wiosnademo.exceptions.GenericException;
import pl.sdacademy.wiosnademo.repositories.ParkingDetailsRepository;
import pl.sdacademy.wiosnademo.repositories.ParkingLotRepository;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;

@Service
@Transactional
public class ParkingLotService {

    private final ParkingLotRepository parkingLotRepository;
    private final ParkingDetailsRepository parkingDetailsRepository;

    public ParkingLotService(final ParkingLotRepository parkingLotRepository, final ParkingDetailsRepository parkingDetailsRepository) {
        this.parkingLotRepository = parkingLotRepository;
        this.parkingDetailsRepository = parkingDetailsRepository;
    }

    public ParkingLot getById(final Long id) {
        return parkingLotRepository.findById(id)
                .orElseThrow(() -> new GenericException("Failed to find parking by id " + id));
    }

    public List<ParkingLot> getAll() {
        return parkingLotRepository.findAll();
    }

    public ParkingLot create(final ParkingLot parkingLot) {
        parkingLotRepository.findByName(parkingLot.getName())
                .ifPresent(pl -> {throw new GenericException("Parking lot with name " + parkingLot.getName() + " already exists");});
        parkingLot.setId(null);
        parkingLot.setDetails(null);
        return parkingLotRepository.create(parkingLot);
    }

    public ParkingLot update(final ParkingLot updatedParkingLot, final Long id) {
        final ParkingLot parkingLot = parkingLotRepository.findById(id)
                .orElseThrow(() -> new GenericException("Parking lot with id " + id + " does not exist"));
        if (!parkingLot.getName().equals(updatedParkingLot.getName())) {
            parkingLotRepository.findByName(updatedParkingLot.getName())
                    .ifPresent(x -> {throw new GenericException("Parking with same name already exists");});
        }

        parkingLot.setAddress(updatedParkingLot.getAddress());
        parkingLot.setName(updatedParkingLot.getName());
        parkingLot.setPlaces(updatedParkingLot.getPlaces());
        return parkingLotRepository.update(parkingLot);
    }

    public void delete(final Long id) {
        parkingLotRepository.findById(id)
                .orElseThrow(() -> new GenericException("Cannot delete parking lot with id " + id + " because it does not exist"));
        parkingLotRepository.deleteById(id);
    }

    public Optional<ParkingLot> findByName(final String name) {
        return parkingLotRepository.findByName(name);
    }

    public ParkingLot createDetailsForParkingLot(final ParkingDetails parkingDetails, final Long parkingId) {
        final Optional<ParkingLot> parkingLotOptional = parkingLotRepository.findById(parkingId);
        if (parkingLotOptional.isEmpty()) {
            throw new GenericException("Cannot add details for non existing parking id " + parkingId);
        }
        final ParkingLot parkingLot = parkingLotOptional.get();
        if (nonNull(parkingLot.getDetails())) {
            throw new GenericException("parking lot with id " + parkingId + " already has details assigned");
        }
        parkingDetailsRepository.findByMobilePhone(parkingDetails.getMobilePhone())
                .ifPresent(existingDetails -> {throw new GenericException("Details with same mobile number exists");});
        parkingDetailsRepository.save(parkingDetails);
        parkingLot.setDetails(parkingDetails);
        return parkingLotRepository.update(parkingLot);
    }
}
