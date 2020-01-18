package pl.sdacademy.wiosnademo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import pl.sdacademy.wiosnademo.domain.ParkingLotDetails;

public interface ParkingLotDetailsRepository extends CrudRepository<ParkingLotDetails, Long> {

  List<ParkingLotDetails> findAllByManagerName(String managerName);

  Optional<ParkingLotDetails> findByMobileNumber(String mobileNumber);
}
