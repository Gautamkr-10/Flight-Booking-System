package com.flightbooking.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;


@Entity
@Data
public class Flight {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String airline;
	private String source;
	private String destination;
	private LocalDateTime departureTime;
	private LocalDateTime arrivalTime;
	private int totalseats;
	private double price;
	
	@OneToMany(mappedBy="flight",cascade=CascadeType.ALL)
    @JsonIgnore
    private List<Booking> bookings;

}
