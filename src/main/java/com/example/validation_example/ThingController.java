package com.example.validation_example;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController // = Controller + ResponseBody
@RequiredArgsConstructor
@RequestMapping("/api/things")
public class ThingController {

    private final ThingService thingService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Thing create(@Valid @RequestBody Thing thing) {
        return thingService.create(thing);
    }
}
