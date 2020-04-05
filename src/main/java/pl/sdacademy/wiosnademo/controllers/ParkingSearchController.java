package pl.sdacademy.wiosnademo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.sdacademy.wiosnademo.domain.ParkingLot;
import pl.sdacademy.wiosnademo.services.ParkingSearchService;

@RestController
@RequestMapping("/api/parking-lots/search")
public class ParkingSearchController {

  private final ParkingSearchService parkingSearchService;

  public ParkingSearchController(final ParkingSearchService parkingSearchService) {
    this.parkingSearchService = parkingSearchService;
  }

  @GetMapping
  public ParkingLot searchByName(@RequestParam(name = "name") final String name) {
    return parkingSearchService.findByName(name);
  }

}
