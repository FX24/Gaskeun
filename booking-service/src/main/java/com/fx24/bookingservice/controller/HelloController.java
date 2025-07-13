package com.fx24.bookingservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        System.out.println("Current thread: " + Thread.currentThread());
        return "Hello from Booking Service!";
    }
}
