package pl.sdacademy.wiosnademo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.sdacademy.wiosnademo.domain.Reservation;

import java.util.Date;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT r FROM reservations r WHERE r.from >= :from and r.to <= :to")
    List<Reservation> findAllInPeriod(@Param("from") final Date from, @Param("to") final Date to);

    @Query("SELECT r FROM reservations r WHERE r.from < :date and r.to > :date")
    List<Reservation> findAllAfterStart(@Param("date")final Date from);

    @Query("SELECT r FROM reservations r WHERE r.from < :date and r.to > :date")
    List<Reservation> findAllBeforeEnd(@Param("date") final Date from);
}
