package com.flightbooking.dao;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.flightbooking.entity.Mode;
import com.flightbooking.entity.Payment;
import com.flightbooking.entity.PaymentStatus;
import com.flightbooking.repository.PaymentRepository;

@Repository
public class PaymentDao {

	@Autowired
	private PaymentRepository paymentRepository;

	public Payment savePayment(Payment payment) {
		return paymentRepository.save(payment);
	}
	
	public List<Payment> fetchAll() {
		return paymentRepository.findAll();
	}

	public Optional<Payment> findById(Integer id) {
		return paymentRepository.findById(id);
	}

	public List<Payment> findPaymentByStatus(PaymentStatus status) {
		return paymentRepository.findByStatus(status);
	}

	public List<Payment> getByAmount(double amount) {
		return paymentRepository.findByAmountGreaterThan(amount);
	}
	
	public List<Payment> getPaymentByMode(Mode mode){
  	 return paymentRepository.findByMode(mode);
   }
	
	public Payment updatePayment(Payment payment) {
   	 return paymentRepository.save(payment);
    }
	
	public Page<Payment>  getPaginationAndSorting(int pageNumber,int pageSize,String field){
 		return paymentRepository.findAll(PageRequest.of(pageNumber, pageSize,Sort.by(field)));
 	}
     

}
