package com.flightbooking.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.flightbooking.dto.ResponseStructure;
import com.flightbooking.entity.Flight;
import com.flightbooking.service.FlightService;

@RestController
@RequestMapping("/flight")
public class FlightController {

	@Autowired
	private FlightService flightService;
	
	//Add Flight
	@PostMapping
	public ResponseEntity<ResponseStructure<Flight>> save(@RequestBody Flight flight) {
		return flightService.saveRecord(flight);
	}

	//Get All Flight
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Flight>>> fetchAll() {
		return flightService.fetchAllDetails();
	}

	//Get Flight By iD
	@GetMapping("/id/{id}")
	public ResponseEntity<ResponseStructure<Flight>> fetchById(@PathVariable Integer id) {
		return flightService.fetchById(id);
	}

	//Get Flight By Source and Destination
	@GetMapping("/{source}/{destination}")
	public ResponseEntity<ResponseStructure<List<Flight>>> fetchBySourceAndDestination(@PathVariable String source,
			@PathVariable String destination) {
		return flightService.fetchBySourceAndDestination(source, destination);
	}

	//Get Flight By Airline
	@GetMapping("/airline/{airline}")
	public ResponseEntity<ResponseStructure<List<Flight>>> fetchByAirline(@PathVariable String airline) {
		return flightService.fetchByAirline(airline);
	}

	//Update Flight
	@PutMapping
	public ResponseEntity<ResponseStructure<Flight>> updateFlight(@RequestBody Flight flight) {
		return flightService.updateFlight(flight);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteFlight(@PathVariable Integer id){
		return flightService.deleteFlight(id);
	}
	
	//Get Flight By Pagination And Sorting
	@GetMapping("/pageandsort/{pageNumber}/{pageSize}/{field}")
    public ResponseEntity<ResponseStructure<Page<Flight>>> getFlightByPageAndSort(@PathVariable Integer pageNumber, @PathVariable Integer pageSize, @PathVariable String field) {
        return flightService.getFlightByPageAndSort(pageNumber, pageSize, field);
    }
}
