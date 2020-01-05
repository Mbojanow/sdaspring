package pl.sdacademy.wiosnademo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.sdacademy.wiosnademo.domain.ParkingLot;
import pl.sdacademy.wiosnademo.model.ParkingLots;
import pl.sdacademy.wiosnademo.services.ParkingLotService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/parking-lots")
public class ParkingLotController {

    private final ParkingLotService parkingLotService;

    public ParkingLotController(final ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    @GetMapping
    public ParkingLots getAll() {
        return new ParkingLots(parkingLotService.getAll());
    }

    @GetMapping(path = "/{id}")
    public ParkingLot get(@PathVariable("id") final Long id) {
        return parkingLotService.getById(id);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") final Long id) {
        parkingLotService.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ParkingLot create(@Valid @RequestBody final ParkingLot parkingLot) {
        return parkingLotService.create(parkingLot);
    }

    @PutMapping("/{id}")
    public ParkingLot update(@Valid @RequestBody final ParkingLot parkingLot, @PathVariable("id") final Long id) {
        return parkingLotService.update(parkingLot, id);
    }
}
