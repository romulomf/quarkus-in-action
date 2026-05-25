package org.acme.billing.model;

import java.time.LocalDate;

import io.quarkus.mongodb.panache.PanacheMongoEntity;

public class InvoiceAdjust extends PanacheMongoEntity {

	public String rentalId;
	
	public String userId;
	
	public LocalDate actualEndDate;
	
	public double price;
	
	public boolean paid;
	
	@Override
	public String toString() {
		return String.format("InvoiceAdjust {rentalId = %s, userId = %s, actualEndDate = %s, price = %f2, paid = %b}", rentalId, userId, actualEndDate, price, paid);
	}
}