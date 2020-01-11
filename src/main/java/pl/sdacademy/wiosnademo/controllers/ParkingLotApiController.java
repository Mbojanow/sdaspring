package pl.sdacademy.wiosnademo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.sdacademy.wiosnademo.domain.ParkingDetails;
import pl.sdacademy.wiosnademo.domain.ParkingLot;
import pl.sdacademy.wiosnademo.model.ParkingLots;
import pl.sdacademy.wiosnademo.services.ParkingLotService;

import javax.validation.Valid;
import java.util.List;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/api/v1/parking-lots")
public class ParkingLotApiController {

    private final ParkingLotService parkingLotService;

    public ParkingLotApiController(final ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    @GetMapping
    public ParkingLots getAll(@RequestParam(name = "name", required = false) final String name) {
        if (isNull(name)) {
            return new ParkingLots(parkingLotService.getAll());
        }

        return parkingLotService.findByName(name)
                .map(x -> new ParkingLots(List.of(x)))
                .orElse(new ParkingLots());
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

    @PostMapping("/{id}/details")
    public ParkingLot addDetails(@PathVariable("id") final Long parkingId, @Valid @RequestBody final ParkingDetails parkingDetails) {
        return parkingLotService.createDetailsForParkingLot(parkingDetails, parkingId);
    }
}
