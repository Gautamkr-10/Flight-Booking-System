package com.flightbooking.dao;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.flightbooking.entity.Passenger;
import com.flightbooking.repository.PassengerRepository;

@Repository
public class PassengerDao {

	@Autowired
	private PassengerRepository passengerRepository;

	// add passenger
	public Passenger savePassenger(Passenger passenger) {
		return passengerRepository.save(passenger);
	}

	// get all passenger
	public List<Passenger> getAllPassenger() {
		return passengerRepository.findAll();
	}

	// get passenger by id
	public Optional<Passenger> getPassengerById(Integer id) {
		return passengerRepository.findById(id);
	}

	// update passenger
	public Passenger updatePassenger(Passenger passenger) {
		return passengerRepository.save(passenger);
	}

	// get passenger by contact number
	public Passenger getPassengerByContactno(Long contactno) {
		return passengerRepository.getByContactno(contactno);
	}

	// paging and sorting
	public Page<Passenger> getPassengerByPageAndSort(int pagenumber, int pagesize, String field) {
		return passengerRepository.findAll(PageRequest.of(pagenumber, pagesize, Sort.by(field).ascending()));
	}

}
