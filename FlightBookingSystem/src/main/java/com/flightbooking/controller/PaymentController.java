package com.flightbooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.flightbooking.dto.ResponseStructure;
import com.flightbooking.entity.Mode;
import com.flightbooking.entity.Payment;
import com.flightbooking.entity.PaymentStatus;
import com.flightbooking.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	
	// Record Payment(save)
	@PostMapping
	public ResponseEntity<ResponseStructure<Payment>> savePayment(@RequestBody Payment payment) {
		return paymentService.saveRecord(payment);
	}
	
	// Get All Payment
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Payment>>> getAllRecord() {
		return paymentService.getAll();
	}
	
	// Get Payment By id
	@GetMapping("/id/{id}")
	public ResponseEntity<ResponseStructure<Payment>> getById(@PathVariable Integer id) {
		return paymentService.getById(id);
	}
	
	// Get Payment By status
	@GetMapping("/status/{status}")
	public ResponseEntity<ResponseStructure<List<Payment>>> getbyStatus(@PathVariable PaymentStatus status) {
		return paymentService.getByStatus(status);
	}

	// Get Payment Greater than a value
	@GetMapping("/amount/{amount}")
	public ResponseEntity<ResponseStructure<List<Payment>>> getByAmoEntity(@PathVariable double amount) {
		return paymentService.getByPayment(amount);
	}
	
	//Get Payment By Mode of Transaction
	@GetMapping("/mode/{mode}")
    public ResponseEntity<ResponseStructure<List<Payment>>> getPaymentByMode(@PathVariable  Mode mode){
  	  return paymentService.getPaymentByMode(mode);
    }
	
	// update Payment
    @PutMapping("/paymentupdate")
    public ResponseEntity<ResponseStructure<Payment>> updatePayment(@RequestBody Payment payment){
  	 return paymentService.updatePayment(payment);
    }
    
    // Pagination and Sorting
    @GetMapping("/paymentsort/{pageNumber}/{pageSize}/{field}")
	public  ResponseEntity<ResponseStructure<Page<Payment>>> getPaginationAndSorting(@PathVariable int pageNumber,@PathVariable int pageSize,@PathVariable String field){
    	return paymentService.getPaginationAndSorting(pageNumber, pageSize, field);
	  }
}
