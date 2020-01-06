package pl.sdacademy.wiosnademo.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sdacademy.wiosnademo.domain.ParkingLot;
import pl.sdacademy.wiosnademo.domain.Reservation;
import pl.sdacademy.wiosnademo.exceptions.GenericException;
import pl.sdacademy.wiosnademo.repositories.ReservationRepository;

@Service
@Transactional
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ParkingLotService parkingLotService;

    public ReservationService(final ReservationRepository reservationRepository, final ParkingLotService parkingLotService) {
        this.reservationRepository = reservationRepository;
        this.parkingLotService = parkingLotService;
    }

    public Reservation createReservation(final Reservation reservation, final Long parkingId) {
        final ParkingLot parkingLot = parkingLotService.getById(parkingId);
        final Long places = parkingLot.getPlaces();
        final int placesTaken = getReservationsCount(reservation);
        if (placesTaken == places) {
            throw new GenericException("Cannot save reservation as there are no parking spaces available");
        }
        reservation.setParkingLot(parkingLot);
        return reservationRepository.save(reservation);
    }

    private int getReservationsCount(final Reservation reservation) {
        return reservationRepository.findAllInPeriod(reservation.getFrom(), reservation.getTo()).size() +
                reservationRepository.findAllAfterStart(reservation.getFrom()).size() +
                reservationRepository.findAllBeforeEnd(reservation.getTo()).size();
    }
}
