package com.flightbooking.controller;

import java.time.LocalDateTime;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.flightbooking.dto.ResponseStructure;
import com.flightbooking.entity.Booking;
import com.flightbooking.entity.BookingStatus;
import com.flightbooking.entity.Passenger;
import com.flightbooking.entity.Payment;
import com.flightbooking.service.BookingService;

@RestController
@RequestMapping("/booking")
public class BookingController {

	@Autowired
	private BookingService bookingService;
	
	//create Booking
	@PostMapping
	public ResponseEntity<ResponseStructure<Booking>> saveBooking(@RequestBody Booking booking) {
		return bookingService.saveBooking(booking);
	}
	
	// Get All Booking
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Booking>>> findAll() {
		return bookingService.getAll();
	}
	
	// Get Booking By id
	@GetMapping("/id/{id}")
	public ResponseEntity<ResponseStructure<Booking>> findById(@PathVariable Integer id){
		return bookingService.findById(id);
	}

	//Get Booking By Flight Id
	@GetMapping("/flight/{id}")
	public ResponseEntity<ResponseStructure<List<Booking>>> findByFlightId(@PathVariable Integer id) {
		return bookingService.findByFlightId(id);
	}
	
	// Get Booking By date5
	@GetMapping("/date/{ldt}")
	public ResponseEntity<ResponseStructure<List<Booking>>> findByDate(@PathVariable LocalDateTime ldt) {
		return bookingService.findByDateAndTime(ldt);
	}

	//Get Booking By status
	@GetMapping("/status/{status}")
	public ResponseEntity<ResponseStructure<List<Booking>>> getBookingsByStatus(@PathVariable BookingStatus status) {
		return bookingService.findByStatus(status);
	}

	// Get All passengers in the booking
	@GetMapping("/passanger/{id}")
	public ResponseEntity<ResponseStructure<List<Passenger>>> getPassengerByBooking(@PathVariable Integer id) {
		return bookingService.getAllPassengerByBooking(id);
	}

	// Get Payment details of a Booking
	@GetMapping("/payment/{id}")
	public ResponseEntity<ResponseStructure<Payment>> getPaymentByBooking(@PathVariable Integer id) {
	    return bookingService.getPaymentdetailByBooking(id);
	}

	// Update Booking Status
	@PutMapping("/{id}/status")
	public ResponseEntity<ResponseStructure<Booking>> updateStatus(@PathVariable Integer id,@RequestParam BookingStatus status) {
		return bookingService.updateBookingStatus(id, status);
	}
	
	// Delete Booking
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteBooking(@PathVariable Integer id) {
	    return bookingService.deleteBooking(id);
	}
	
	// Pagination and Sorting
	@GetMapping("/pagisort/{pageNumber}/{pageSize}/{field}")
	public ResponseEntity<ResponseStructure<Page<Booking>>> getbookingPaginatinAndSorting( @PathVariable int pageNumber,@PathVariable int pageSize,@PathVariable String field){
		return bookingService.getbookingPaginatinAndSorting(pageNumber, pageSize, field);
	}
}
