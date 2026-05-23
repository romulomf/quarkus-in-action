package org.acme.reservation.billing;

import org.acme.reservation.entity.Reservation;

public class Invoice {

	public Reservation reservation;
	
	public double price;

	public Invoice(Reservation reservation, double price) {
		this.reservation = reservation;
		this.price = price;
	}

	@Override
	public String toString() {
		return String.format("Invoice {reservation = %s, price = %f}", reservation, price);
	}
}