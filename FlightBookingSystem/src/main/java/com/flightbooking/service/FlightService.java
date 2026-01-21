package com.flightbooking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.flightbooking.dao.FlightDao;
import com.flightbooking.dto.ResponseStructure;
import com.flightbooking.entity.Flight;
import com.flightbooking.exception.NoRecordFoundException;

@Service
public class FlightService {

	@Autowired
	private FlightDao flightDao;

	public ResponseEntity<ResponseStructure<Flight>> saveRecord(Flight flight) {
		Flight flight1 = flightDao.saveRecord(flight);
		ResponseStructure<Flight> response = new ResponseStructure<>();
		response.setData(flight1);
		response.setMessage("Data Saved Sucessfully");
		response.setStatusCode(HttpStatus.CREATED.value());
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<List<Flight>>> fetchAllDetails() {
		ResponseStructure<List<Flight>> response = new ResponseStructure<>();
		List<Flight> fl = flightDao.fetchAll();
		if (!fl.isEmpty()) {
			response.setData(fl);
			response.setMessage("Data Retrived sucessfully");
			response.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		throw new NoRecordFoundException("No record found");

	}

	public ResponseEntity<ResponseStructure<Flight>> fetchById(Integer id) {
		ResponseStructure<Flight> response = new ResponseStructure<>();
		Optional<Flight> optional = flightDao.fetchById(id);

		if (optional.isPresent()) {
			response.setData(optional.get());
			response.setMessage("Data retrieved successfully");
			response.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		throw new NoRecordFoundException("Flight not found for ID: " + id);
	}

	public ResponseEntity<ResponseStructure<List<Flight>>> fetchBySourceAndDestination(String source,
			String destination) {
		ResponseStructure<List<Flight>> response = new ResponseStructure<>();
		List<Flight> flights = flightDao.fetchBySourceAndDestination(source, destination);
		if (!flights.isEmpty()) {
			response.setData(flights);
			response.setMessage("Data fetched sucessfully");
			response.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		throw new NoRecordFoundException("No Record Available");
	}

	public ResponseEntity<ResponseStructure<List<Flight>>> fetchByAirline(String airline) {
		ResponseStructure<List<Flight>> response = new ResponseStructure<>();
		List<Flight> flights = flightDao.fetchByAirline(airline);
		if (!flights.isEmpty()) {
			response.setData(flights);
			response.setMessage("Data fetched sucessfully");
			response.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		throw new NoRecordFoundException("No Record Available");
	}

	public ResponseEntity<ResponseStructure<Flight>> updateFlight(Flight flight) {
		ResponseStructure<Flight> responseStructure = new ResponseStructure<>();
		Optional<Flight> optional = flightDao.fetchById(flight.getId());
		if (!optional.isEmpty()) {
			Flight flight1 = flightDao.updateFlight(flight);
			responseStructure.setData(flight1);
			responseStructure.setMessage("Data Updated Sucessfully");
			responseStructure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		}
		throw new NoRecordFoundException("No Record available");
	}

	public ResponseEntity<ResponseStructure<String>> deleteFlight(Integer id) {
		Optional<Flight> optional = flightDao.fetchById(id);
		if (optional.isPresent()) {
			flightDao.deleteRecord(id);
			ResponseStructure<String> response = new ResponseStructure<>();
			response.setData("Flight deleted successfully");
			response.setMessage("Deleted");
			response.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		throw new NoRecordFoundException("Flight not found for ID: " + id);
	}

	// Get Flight with pagination & sorting
	public ResponseEntity<ResponseStructure<Page<Flight>>> getFlightByPageAndSort(int pageNumber, int pageSize,
			String field) {

		Page<Flight> flight = flightDao.getFlightByPageAndSort(pageNumber, pageSize, field);
		ResponseStructure<Page<Flight>> response = new ResponseStructure<>();

		if (!flight.getContent().isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Flight retrieved successfully with pagination and sorting by " + field + "!");
			response.setData(flight);

			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			throw new NoRecordFoundException("No Flight records found!");
		}
	}

}
