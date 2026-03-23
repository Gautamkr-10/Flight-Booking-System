package com.flightbooking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.flightbooking.entity.Booking;
import com.flightbooking.entity.Flight;
import com.flightbooking.entity.Passenger;

@Service
public class EmailNotificationService {
	@Autowired
    private JavaMailSender mailSender;

    public void sendBookingConfirmation(String toEmail, Booking booking) {
    	
    	Passenger passenger = booking.getPassengers().get(0);
        Flight flight = booking.getFlight();

        String emailBody =
                "Hello " + passenger.getName() + ",\n\n" +
                "✈ Your Flight Booking is CONFIRMED!\n\n" +
                		
				"📘 Booking Details\n" +
				"----------------------------\n" +
				"Booking ID     : " + booking.getId() + "\n" +
				"Passenger Name : " + passenger.getName() + "\n" +
				"Gender         : " + passenger.getGender() + "\n" +
				"Seat Number    : " + passenger.getSeatNumber() + "\n\n" +
				
				"✈ Flight Details\n" +
				"----------------------------\n" +
				"Airline        : " + flight.getAirline() + "\n" +
				"From           : " + flight.getSource() + "\n" +
				"To             : " + flight.getDestination() + "\n" +
				"Departure Time : " + flight.getDepartureTime() + "\n" +
				"Arrival Time   : " + flight.getArrivalTime() + "\n" +
				"Price          : ₹"+ flight.getPrice() + "\n\n" +
				
				"Status         : CONFIRMED\n\n" +
				"Thank you for choosing our Flight Booking System.\n\n" +
				"Have a safe journey! ✨";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("✈ Flight Booking Confirmed");
        message.setText(emailBody);
        

        mailSender.send(message);
    }

}
