package com.flightbooking.service;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flightbooking.dao.BookingDao;
import com.flightbooking.dto.ResponseStructure;
import com.flightbooking.entity.Booking;
import com.flightbooking.entity.BookingStatus;
import com.flightbooking.entity.Flight;
import com.flightbooking.entity.Passenger;
import com.flightbooking.entity.Payment;
import com.flightbooking.exception.NoRecordFoundException;
import com.flightbooking.exception.PassengerNotFoundException;
import com.flightbooking.exception.PaymentRecordNotFoundException;
import com.flightbooking.repository.FlightRepository;

@Service
public class BookingService {

	@Autowired
	private BookingDao bookingDao;

	private Booking booking;

	@Autowired
	private FlightRepository flightRepository;

	@Transactional

	public ResponseEntity<ResponseStructure<Booking>> saveBooking(Booking booking) {
		Optional<Flight> opt = flightRepository.findById(booking.getFlight().getId());
		if (opt.isEmpty()) {
			throw new NoRecordFoundException("Flight record not found");
		}
		Flight flight = opt.get();
		booking.setFlight(flight);
		if (booking.getPassengers() == null || booking.getPassengers().isEmpty()) {
			throw new PassengerNotFoundException("Passenger is not there  book a ticket");
		}
		if (booking.getPayment() == null) {
			throw new PaymentRecordNotFoundException("Payment is not there for booking");
		}
		List<Passenger> passengers = booking.getPassengers();
		int totalPassenger = passengers.size();
		double pricePerTicket = flight.getPrice();
		double totalAmount = totalPassenger * pricePerTicket;
		Payment payment = booking.getPayment();
		payment.setAmount(totalAmount);
		payment.setBooking(booking);
		for (Passenger p : passengers) {
			p.setBooking(booking);
		}
		booking.setStatus(BookingStatus.CONFIRMED);
		Booking savedBooking = bookingDao.saveBooking(booking);
		ResponseStructure<Booking> response = new ResponseStructure<>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Booking Success");
		response.setData(savedBooking);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<List<Booking>>> getAll() {
		List<Booking> bList = bookingDao.getAll();
		ResponseStructure<List<Booking>> response = new ResponseStructure<>();
		if (!bList.isEmpty()) {
			response.setData(bList);
			response.setMessage("All bookings are avasilable");
			response.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		throw new NoRecordFoundException("NO record available");
	}

	public ResponseEntity<ResponseStructure<Booking>> findById(Integer id) {
		Optional<Booking> optional = bookingDao.getById(id);
		ResponseStructure<Booking> response = new ResponseStructure<>();
		if (optional.isPresent()) {
			response.setData(optional.get());
			response.setMessage("Data retrived sucessfully");
			response.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		throw new NoRecordFoundException("No Record available");
	}

	public ResponseEntity<ResponseStructure<List<Booking>>> findByFlightId(Integer flightId) {
		List<Booking> bookings = bookingDao.getByFlightId(flightId);
		ResponseStructure<List<Booking>> response = new ResponseStructure<>();

		if (!bookings.isEmpty()) {
			response.setData(bookings);
			response.setMessage("Bookings found for flight ID: " + flightId);
			response.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		throw new NoRecordFoundException("No bookings found for flight ID: " + flightId);
	}

	public ResponseEntity<ResponseStructure<List<Booking>>> findByDateAndTime(LocalDateTime bookingDate) {
		List<Booking> booking = bookingDao.getByDateAndTime(bookingDate);
		ResponseStructure<List<Booking>> response = new ResponseStructure<>();
		if (!booking.isEmpty()) {
			response.setData(booking);
			response.setMessage("Bookings found by date: " + bookingDate);
			response.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		throw new NoRecordFoundException("No bookings found for date " + bookingDate);

	}

	public ResponseEntity<ResponseStructure<List<Booking>>> findByStatus(BookingStatus status) {
		List<Booking> bookings = bookingDao.getByStatus(status);
		ResponseStructure<List<Booking>> response = new ResponseStructure<>();

		if (!bookings.isEmpty()) {
			response.setData(bookings);
			response.setMessage("Bookings with status: " + status);
			response.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		throw new NoRecordFoundException("No bookings found with status: " + status);
	}

	public ResponseEntity<ResponseStructure<List<Passenger>>> getAllPassengerByBooking(Integer id) {
		ResponseStructure<List<Passenger>> response = new ResponseStructure<>();

		List<Passenger> passengers = bookingDao.getAllPassengerByBooking(id);
		if (!passengers.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Passengers fetched successfully");
			response.setData(bookingDao.getAllPassengerByBooking(id));
			return new ResponseEntity<ResponseStructure<List<Passenger>>>(response, HttpStatus.OK);
		} else
			throw new NoRecordFoundException("record is not avaliable");
	}

	public ResponseEntity<ResponseStructure<Payment>> getPaymentdetailByBooking(Integer id) {

		Payment payment = bookingDao.getPaymentDetailsByBookingId(id);

		ResponseStructure<Payment> response = new ResponseStructure<>();

		if (payment != null) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Payment fetched successfully");
			response.setData(payment);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setMessage("Payment info not found (invalid booking ID)");
			response.setData(null);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<Booking>> updateBookingStatus(Integer id, BookingStatus status) {
		Optional<Booking> optional = bookingDao.getById(id);
		ResponseStructure<Booking> response = new ResponseStructure<>();

		if (optional.isPresent()) {
			Booking booking = optional.get();
			booking.setStatus(status);

			bookingDao.saveBooking(booking);

			response.setData(booking);
			response.setMessage("Booking status updated to: " + status);
			response.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		throw new NoRecordFoundException("Booking ID not found: " + id);
	}

	public ResponseEntity<ResponseStructure<String>> deleteBooking(Integer id) {
		ResponseStructure<String> response = new ResponseStructure<>();

		if (booking != null && booking.getId() != 0) {
			bookingDao.delete(booking);
			response.setData("Booking deleted successfully");
			response.setMessage("Deleted booking ID: " + booking.getId());
			response.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		throw new NoRecordFoundException("Invalid booking object or missing ID");
	}

	public ResponseEntity<ResponseStructure<Page<Booking>>> getbookingPaginatinAndSorting(int pageNumber, int pageSize,
			String field) {
		ResponseStructure<Page<Booking>> response = new ResponseStructure<>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("All  flignts are pagination and sorting is done ");
		response.setData(bookingDao.getBookingPaginationAndSorting(pageNumber, pageSize, field));
		return new ResponseEntity<ResponseStructure<Page<Booking>>>(response, HttpStatus.OK);
	}

}
