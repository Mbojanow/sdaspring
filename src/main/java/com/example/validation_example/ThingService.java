package com.example.validation_example;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ThingService {

    private final ThingRepository thingRepository;

    public Thing create(Thing thing) {
        if (thing.getName().length() < 10) {
            throw new SdaException("Boom, name too short");
        }
        thing.setId(null);
        return thingRepository.save(thing);
    }

    public void remove(Long id) {
        thingRepository.deleteById(id);
    }
}
