package com.flightbooking.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.flightbooking.entity.Mode;
import com.flightbooking.entity.Payment;
import com.flightbooking.entity.PaymentStatus;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
	
	List<Payment> findByStatus(PaymentStatus status);
	List<Payment> findByAmountGreaterThan(double amount);
	List<Payment> findByMode(Mode mode);

}
