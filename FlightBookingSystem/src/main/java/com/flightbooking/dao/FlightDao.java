package com.flightbooking.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.flightbooking.entity.Flight;
import com.flightbooking.repository.FlightRepository;

@Repository
public class FlightDao {

	@Autowired
	private FlightRepository flightRepository;

	public Flight saveRecord(Flight flight) {
		return flightRepository.save(flight);
	}

	public List<Flight> fetchAll() {
		return flightRepository.findAll();
	}

	public Optional<Flight> fetchById(Integer id) {
		return flightRepository.findById(id);
	}

	public List<Flight> fetchBySourceAndDestination(String source, String destination) {
		return flightRepository.findBySourceAndDestination(source, destination);
	}

	public List<Flight> fetchByAirline(String airline) {
		return flightRepository.findByAirline(airline);
	}

	public Flight updateFlight(Flight flight) {
		return flightRepository.save(flight);
	}

	public String deleteRecord(Integer id) {
		flightRepository.deleteById(id);
		return "Record Deleted Sucessfully";
	}
	
	// paging and sorting
		public Page<Flight> getFlightByPageAndSort(int pagenumber, int pagesize, String field) {
			return flightRepository.findAll(PageRequest.of(pagenumber, pagesize, Sort.by(field).ascending()));
		}
}
