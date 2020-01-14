package pl.sdacademy.wiosnademo.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dummy")
public class DummyController {

    @GetMapping
    public String hi() {
        return "Hello";
    }

    @PostMapping()
    public String hello() {
        return "hello";
    }

    @PutMapping
    public String put() {
        return "put";
    }

    @PatchMapping
    public String patch() {
        return "patch";
    }

    @DeleteMapping
    public String delete() {
        return "delete";
    }
}
