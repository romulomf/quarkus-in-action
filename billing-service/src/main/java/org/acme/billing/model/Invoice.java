package org.acme.billing.model;

import java.time.LocalDate;

import io.quarkus.mongodb.panache.PanacheMongoEntity;

public class Invoice extends PanacheMongoEntity {

	public static final class Reservation {
		
		public Long id;
		
		public String userId;
		
		public Long carId;
		
		public LocalDate startDay;
		
		public LocalDate endDay;
		
		public Reservation(Long id, String userId, Long carId, LocalDate startDay, LocalDate endDay) {
			this.id = id;
			this.userId = userId;
			this.carId = carId;
			this.startDay = startDay;
			this.endDay = endDay;
		}
		
		@Override
		public String toString() {
			return String.format("Reservation {id = %d, userId = %s, carId = %d, startDay = %s, endDay = %s}", id, userId, carId, startDay, endDay);
		}
	}

	public double totalPrice;
	
	public boolean paid;

	public Reservation reservation;

	public Invoice(double totalPrice, boolean paid, Reservation reservation) {
		this.totalPrice = totalPrice;
		this.paid = paid;
		this.reservation = reservation;
	}

	@Override
	public String toString() {
		return String.format("Invoice {totalPrice = %f2, paid = %b, reservation = %s}", totalPrice, paid, reservation);
	}
}