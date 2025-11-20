package com.flightbooking.dao;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.flightbooking.entity.Booking;
import com.flightbooking.entity.BookingStatus;
import com.flightbooking.entity.Passenger;
import com.flightbooking.entity.Payment;
import com.flightbooking.repository.BookingRepository;

@Repository
public class BookingDao {

	@Autowired
	private BookingRepository bookingRepository;

	public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

	public List<Booking> getAll() {
		return bookingRepository.findAll();
	}

	public Optional<Booking> getById(Integer id) {
		return bookingRepository.findById(id);
	}

	public List<Booking> getByFlightId(Integer id) {
		return bookingRepository.findByFlightId(id);
	}

	public List<Booking> getByDateAndTime(LocalDateTime bookingDate) {
		return bookingRepository.findByBookingDate(bookingDate);
	}

	public List<Booking> getByStatus(BookingStatus status) {
		return bookingRepository.findByStatus(status);
	}

	public List<Passenger> getAllPassengerByBooking(Integer id){
		return bookingRepository.getAllPassengerByBooking(id);
	}
	
	public Payment getPaymentDetailsByBookingId(Integer id) {
	    return bookingRepository.getPaymentDetailByBookingId(id);
	}
	
	public Booking updateBookingStatus(Booking booking) {
		return bookingRepository.save(booking);
	}
	
	public String delete(Booking booking) {
		bookingRepository.delete(booking);
		return "Booking canceled sucessfully";
	}
	 
	public Page<Booking> getBookingPaginationAndSorting(int pageNumber,int pageSize,String field){
   	 	return bookingRepository.findAll(PageRequest.of(pageNumber, pageSize,Sort.by(field)));
    }

}
