package pl.sdacademy.wiosnademo.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pl.sdacademy.wiosnademo.domain.Reservation;
import pl.sdacademy.wiosnademo.services.ReservationService;

@RestController
@RequestMapping("/api/parking-lots")
public class ReservationController {

  private final ReservationService reservationService;

  public ReservationController(final ReservationService reservationService) {
    this.reservationService = reservationService;
  }

  @PostMapping("/{id}/reservations")
  @ResponseStatus(HttpStatus.CREATED)
  public Reservation createReservation(@PathVariable("id") final Long parkingId,
                                       @Valid @RequestBody final Reservation reservation) {
    return reservationService.create(parkingId, reservation);
  }
}
