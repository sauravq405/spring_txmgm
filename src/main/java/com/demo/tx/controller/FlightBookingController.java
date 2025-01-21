package com.demo.tx.controller;

import com.demo.tx.dto.FlightBookingAcknowledgement;
import com.demo.tx.dto.FlightBookingRequest;
import com.demo.tx.service.FlightBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class FlightBookingController {
    @Autowired
    private FlightBookingService flightBookingService;

    @PostMapping("/bookFlightTicket")
    public FlightBookingAcknowledgement bookFlightTicket(@RequestBody FlightBookingRequest request){
        return flightBookingService.bookFlightTicket(request);
    }
}
