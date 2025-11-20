package com.flightbooking.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Payment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@CreationTimestamp
	private LocalDate paymentDate;
	
	private double amount;
	
	@Enumerated(EnumType.STRING)
	private Mode mode;
	
	@Enumerated(EnumType.STRING)
	private PaymentStatus status;
	
	@OneToOne
    @JoinColumn
    @JsonIgnore
    private Booking booking;
}


