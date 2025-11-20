package com.flightbooking.repository;

import java.time.LocalDateTime;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.flightbooking.entity.Booking;
import com.flightbooking.entity.BookingStatus;
import com.flightbooking.entity.Passenger;
import com.flightbooking.entity.Payment;

public interface BookingRepository extends JpaRepository<Booking, Integer>{
	
	List<Booking> findByFlightId(Integer flightId);
	List<Booking> findByBookingDate(LocalDateTime bookingDate);
	List<Booking> findByStatus(BookingStatus status);
	
	@Query("select b.passengers from Booking b where b.id = ?1")
	List<Passenger> getAllPassengerByBooking(Integer id);

	
	@Query("select b.payment from Booking b where b.id = ?1")
    Payment getPaymentDetailByBookingId(Integer id);

}
