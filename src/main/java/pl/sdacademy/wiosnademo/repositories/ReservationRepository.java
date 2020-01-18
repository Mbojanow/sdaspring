package pl.sdacademy.wiosnademo.repositories;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import pl.sdacademy.wiosnademo.domain.Reservation;

public interface ReservationRepository /*extends CrudRepository<Reservation, Long>*/ {

  @Query("SELECT COUNT(r) FROM reservations r WHERE r.from <= :date AND r.to > :date")
  Long countReservationsWithTimesBetweenFrom(@Param("date") final Date date);

  @Query("SELECT COUNT(r) FROM reservations r WHERE r.from >= :date AND r.to < :date")
  Long countReservationsWithTimesBetweenTo(@Param("date") final Date date);

  @Query("SELECT COUNT(r) FROM reservations r WHERE r.from <= :date1 AND r.to > :date2")
  Long countReservationWithTimesOutsideOfPeriod(@Param("date1") final Date date1,
                                                @Param("date2") final Date date2);

  @Query("SELECT COUNT(r) FROM reservations r WHERE r.from >= :date1 AND r.to < :date2")
  Long countReservationWithTimesInsideOfPeriod(@Param("date1") final Date date1,
                                               @Param("date2") final Date date2);

}
