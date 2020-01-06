package pl.sdacademy.wiosnademo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import pl.sdacademy.wiosnademo.domain.ParkingDetails;

import java.util.Optional;

public interface ParkingDetailsRepository extends JpaRepository<ParkingDetails, Long> {

    Optional<ParkingDetails> findByMobilePhone(@Nullable final String mobilePhone);

    Optional<ParkingDetails> findByManagerName(final String managerName);
}
