package pl.sdacademy.wiosnademo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.sdacademy.wiosnademo.domain.ParkingDetails;

// NIE MUSI BYC OZNACZONY STEREOTYPE!!!
public interface ParkingDetailsRepository extends JpaRepository<ParkingDetails, Long> {

  Optional<ParkingDetails> findByManagerNameAndPhoneNumber(String managerName, String phoneNumber);

  // SELECT * FROM ParkingDetails WHERE phone_number = ?
  Optional<ParkingDetails> findByPhoneNumber(String phoneNumber);
}
