package com.flightbooking.entity;

import jakarta.persistence.*;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Data
public class Passenger {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private int age;
	private String gender;
	private String seatNumber;
	private long contactno;
	
	@ManyToOne
    @JoinColumn
    @JsonIgnore
    private Booking booking;
	
}
