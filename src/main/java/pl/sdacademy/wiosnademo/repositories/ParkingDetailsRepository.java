package pl.sdacademy.wiosnademo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;

import pl.sdacademy.wiosnademo.domain.ParkingDetails;

                                                 //MongoCrudRepository
public interface ParkingDetailsRepository extends JpaRepository<ParkingDetails, Long> {

  Optional<ParkingDetails> findByPhoneNumber(String phoneNumber);

  Optional<ParkingDetails> findByManagerNameAndPhoneNumber(@Nullable String managerName, String phoneNumber);

  List<ParkingDetails> findAllByManagerName(String managerName);

  @Query("SELECT pd FROM parking_details pd WHERE pd.managerName IS NULL")
  List<ParkingDetails> findAllByUnassingedManagerName();
}
