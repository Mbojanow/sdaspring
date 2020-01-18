package pl.sdacademy.wiosnademo.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pl.sdacademy.wiosnademo.domain.ParkingLot;
import pl.sdacademy.wiosnademo.domain.ParkingLotDetails;
import pl.sdacademy.wiosnademo.services.ParkingLotDetailsService;

@RestController
@RequestMapping("/api/parking-lots")
public class ParkingLotDetailsController {

  private final ParkingLotDetailsService parkingLotDetailsService;

  public ParkingLotDetailsController(final ParkingLotDetailsService parkingLotDetailsService) {
    this.parkingLotDetailsService = parkingLotDetailsService;
  }

  @PostMapping(path = "/{id}/details")
  @ResponseStatus(HttpStatus.CREATED)
  public ParkingLot addDetailsToParking(@PathVariable("id")final Long parkingId,
                                        @Valid @RequestBody final ParkingLotDetails details) {
    return parkingLotDetailsService.addDetails(parkingId, details);
  }

}
