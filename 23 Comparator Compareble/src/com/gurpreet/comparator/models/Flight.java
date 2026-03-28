package com.gurpreet.comparator.models;

public class Flight implements Comparable<Flight> {

	private String airline;
	private double fare;
	
	
	public Flight(String airline, double fare) {
		if(airline == null || airline.trim().isEmpty()) {
			throw new IllegalArgumentException("Airline Can't be empty");
		}
		
		if(fare < 0) {
			throw new IllegalArgumentException("Fare can't be negative");
		}
		this.airline = airline;
		this.fare = fare;
	}
	public String getAirline() {
		return airline;
	}
	
	public double getFare() {
		return fare;
	}
	@Override
	public int compareTo(Flight flight) {
		
		return Double.compare(flight.fare, this.fare);
	}
	
	
	
}
