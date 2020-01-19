package pl.sdacademy.wiosnademo.services;

import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.sdacademy.wiosnademo.domain.ParkingLot;
import pl.sdacademy.wiosnademo.domain.Reservation;
import pl.sdacademy.wiosnademo.exceptions.ParkingLotGenericException;
import pl.sdacademy.wiosnademo.repositories.ReservationRepository;

@Service
@Transactional
public class ReservationService {

  private final ReservationRepository reservationRepository;
  private final ParkingLotService parkingLotService;

  public ReservationService(final ReservationRepository reservationRepository,
                            final ParkingLotService parkingLotService) {
    this.reservationRepository = reservationRepository;
    this.parkingLotService = parkingLotService;
  }

  public Reservation create(final Long parkingId, final Reservation reservation) {
    final ParkingLot existingParking = parkingLotService.findById(parkingId);
    final Long totalPlaces = existingParking.getPlaces();
    if (totalPlaces.equals(getTotalReservationsCountInPeriod(reservation.getFrom(), reservation.getTo()))) {
      throw new ParkingLotGenericException("Reservation cannot be made, all places taken");
    }
    reservation.setParkingLot(existingParking);
    return reservationRepository.save(reservation);
  }

  private Long getTotalReservationsCountInPeriod(final Date start, final Date end) {
    return reservationRepository.countReservationsWithTimesBetweenFrom(start, end) +
        reservationRepository.countReservationsWithTimesBetweenTo(start, end) +
        reservationRepository.countReservationWithTimesInsideOfPeriod(start, end) +
        reservationRepository.countReservationWithTimesOutsideOfPeriod(start, end);
  }
}
