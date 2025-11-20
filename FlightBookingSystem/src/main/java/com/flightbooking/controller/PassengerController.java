package com.flightbooking.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.flightbooking.dto.ResponseStructure;
import com.flightbooking.entity.Passenger;
import com.flightbooking.service.PassengerService;

@RestController
@RequestMapping("/passenger")
public class PassengerController {
	
	@Autowired
    private PassengerService passengerService;

    @PostMapping
    public ResponseEntity<ResponseStructure<Passenger>> savePassenger(@RequestBody Passenger passenger) {
        return passengerService.savePassnger(passenger);
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseStructure<List<Passenger>>> getAllPassenger() {
        return passengerService.getAllPassenger();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ResponseStructure<Passenger>> getPassengerById(@PathVariable Integer id) {
        return passengerService.getPassengerById(id);
    }

    @PutMapping
    public ResponseEntity<ResponseStructure<Passenger>> updatePassenger(@RequestBody Passenger passenger) {
        return passengerService.updatePassenger(passenger);
    }

    @GetMapping("/contactno/{contactno}")
    public ResponseEntity<ResponseStructure<Passenger>> getPassengerByContactno(@PathVariable Long contactno) {
        return passengerService.getPassengerByContactno(contactno);
    }

    @GetMapping("/pageandsort/{pageNumber}/{pageSize}/{field}")
    public ResponseEntity<ResponseStructure<Page<Passenger>>> getPassengerByPageAndSort(
            @PathVariable Integer pageNumber, @PathVariable Integer pageSize, @PathVariable String field) {
        return passengerService.getPassengerByPageAndSort(pageNumber, pageSize, field);
    }

}
