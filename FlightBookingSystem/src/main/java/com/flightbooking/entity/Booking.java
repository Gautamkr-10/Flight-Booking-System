package com.flightbooking.entity;

import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@CreationTimestamp
	private LocalDateTime bookingDate;

	@Enumerated(EnumType.STRING)
	private BookingStatus status;

	@ManyToOne
	@JoinColumn
	private Flight flight;

	@OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
	private List<Passenger> passengers;

	@OneToOne(mappedBy = "booking", cascade = CascadeType.ALL)
	private Payment payment;



}
