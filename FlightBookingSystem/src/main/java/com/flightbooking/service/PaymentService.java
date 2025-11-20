package com.flightbooking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.flightbooking.dao.PaymentDao;
import com.flightbooking.dto.ResponseStructure;
import com.flightbooking.entity.Mode;
import com.flightbooking.entity.Payment;
import com.flightbooking.entity.PaymentStatus;
import com.flightbooking.exception.NoRecordFoundException;

@Service
public class PaymentService {

	@Autowired
	private PaymentDao paymentDao;

	public ResponseEntity<ResponseStructure<Payment>> saveRecord(Payment payment) {
		ResponseStructure<Payment> response = new ResponseStructure<>();
		response.setData(paymentDao.savePayment(payment));
		response.setMessage("Data Saved sucessfully");
		response.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<Payment>>> getAll() {
		ResponseStructure<List<Payment>> response = new ResponseStructure<>();
		List<Payment> pli = paymentDao.fetchAll();
		if (pli.size() > 0) {
			response.setData(pli);
			response.setMessage("Data fetched sucessfully");
			response.setStatusCode(HttpStatus.OK.value());
		}
		throw new NoRecordFoundException("No Record Available!");

	}

	public ResponseEntity<ResponseStructure<Payment>> getById(Integer id) {
		ResponseStructure<Payment> response = new ResponseStructure<>();
		Optional<Payment> optional = paymentDao.findById(id);
		if (optional.isPresent()) {
			response.setData(optional.get());
			response.setMessage("Data fetched sucessfully");
			response.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		throw new NoRecordFoundException("No Record Available!");
	}

	public ResponseEntity<ResponseStructure<List<Payment>>> getByStatus(PaymentStatus status) {
		ResponseStructure<List<Payment>> response = new ResponseStructure<>();
		List<Payment> pList = paymentDao.findPaymentByStatus(status);
		if (pList.size() > 0) {
			response.setData(pList);
			response.setMessage("Data fetched sucessfully");
			response.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		throw new NoRecordFoundException("No Reord Found!");
	}

	public ResponseEntity<ResponseStructure<List<Payment>>> getByPayment(double amount) {
		ResponseStructure<List<Payment>> response = new ResponseStructure<>();
		List<Payment> pList = paymentDao.getByAmount(amount);
		if (pList.size() > 0) {
			response.setData(pList);
			response.setMessage("Data fetched sucessfully");
			response.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		throw new NoRecordFoundException("No Reord available");
	}
	
	public ResponseEntity<ResponseStructure<List<Payment>>> getPaymentByMode(Mode mode){
		 ResponseStructure<List<Payment>> response=new ResponseStructure<List<Payment>>();
		List<Payment> paymt=paymentDao.getPaymentByMode(mode);
		if(!paymt.isEmpty()) {
			 response.setStatusCode(HttpStatus.OK.value());
				response.setMessage(" All payment are fetched by mode");
				response.setData(paymentDao.getPaymentByMode(mode)); 
				return new ResponseEntity<ResponseStructure<List<Payment>>>(response,HttpStatus.OK);
		 }
		 else {
			 response.setStatusCode(HttpStatus.NOT_FOUND.value());
				response.setMessage(" payments are not avaliable ");
				response.setData(null); 
				return new ResponseEntity<ResponseStructure<List<Payment>>>(response,HttpStatus.NOT_FOUND); 
		 }
	 }
	 
	 
	 public ResponseEntity<ResponseStructure<Payment>> updatePayment(Payment payment){
		 ResponseStructure<Payment> response=new ResponseStructure<>();
   	 if(payment.getId()==null) {
   		 response.setStatusCode(HttpStatus.NOT_FOUND.value());
      	    response.setMessage("id must be passed to paymnet  is updated");
      	    response.setData(null); 
      	    return new ResponseEntity<ResponseStructure<Payment>>(response,HttpStatus.NOT_FOUND);
   	 }
   	Optional<Payment> opt=paymentDao.findById(payment.getId());
   	 if(opt.isPresent()){
   		 response.setStatusCode(HttpStatus.OK.value());
     	    response.setMessage(" Payment is update sucessfully");
     	    response.setData(paymentDao.updatePayment(payment));
     	    return new ResponseEntity<ResponseStructure<Payment>>(response,HttpStatus.OK); 
   	 }
   	 else {
   		 response.setStatusCode(HttpStatus.NOT_FOUND.value());
       	    response.setMessage("invalid id");
       	    response.setData(null); 
       	    return new ResponseEntity<ResponseStructure<Payment>>(response,HttpStatus.NOT_FOUND);
   	 }
	 }
	 
	 public  ResponseEntity<ResponseStructure<Page<Payment>>> getPaginationAndSorting(int pageNumber,int pageSize,String field){
			ResponseStructure<Page<Payment>> response=new ResponseStructure<Page<Payment>>();
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Paymnet pagination and sorting is done");
			response.setData(paymentDao.getPaginationAndSorting(pageNumber, pageSize, field));
		     return new ResponseEntity<ResponseStructure<Page<Payment>>>(response,HttpStatus.OK);
		}


}
