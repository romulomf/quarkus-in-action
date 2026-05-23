package org.acme.rental.billing;

import java.time.LocalDate;

public class InvoiceAdjust {

	public String rentalId;
	
	public String userId;

	public LocalDate actualEndDate;
	
	public double price;
	
	public InvoiceAdjust(String rentalId, String userId, LocalDate actualEndDate, double price) {
		this.rentalId = rentalId;
		this.userId = userId;
		this.actualEndDate = actualEndDate;
		this.price = price;
	}

	@Override
	public String toString() {
		return String.format("InvoiceAdjust {rentalId = %s, userId = %s, actualEndDate = %s, price = %f2}", rentalId, userId, actualEndDate, price);
	}
}