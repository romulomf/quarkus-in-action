package org.acme.rental.invoice.data;

import java.time.LocalDate;

public class InvoiceConfirmation {

	public static final class Invoice {
		
		public boolean paid;
		
		public InvoiceReservation reservation;
		
		@Override
		public String toString() {
			return String.format("Invoice {paid = %b, reservation = %s}", paid, reservation);
		}
	}
	
	public static final class InvoiceReservation {
		
		public Long id;
		
		public String userId;
		
		public LocalDate startDay;
		
		@Override
		public String toString() {
			return String.format("InvoiceReservation {id = %d, userId = %s, startDay = %s}", id, userId, startDay);
		}
	}

	public Invoice invoice;
	
	public boolean paid;
}