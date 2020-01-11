package pl.sdacademy.wiosnademo.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sdacademy.wiosnademo.domain.Reservation;
import pl.sdacademy.wiosnademo.services.ReservationService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/reservations")
public class ReservationApiController {

    private final ReservationService reservationService;

    public ReservationApiController(final ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public Reservation create(@Valid @RequestBody final Reservation reservation) {
        return reservationService.createReservation(reservation, 1L);
    }
}
