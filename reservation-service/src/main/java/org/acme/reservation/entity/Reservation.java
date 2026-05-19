package org.acme.reservation.entity;

import java.time.LocalDate;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class Reservation extends PanacheEntity {

	public Long carId;
	
	public String userId;
	
	public LocalDate startDay;
	
	public LocalDate endDay;
	
	/**
	 * Check if the given duration overlaps with this reservation.
	 * 
	 * @param startDay reservation start
	 * @param endDay reservation end
	 * 
	 * @return {@code true} if the dates overlap with the reservation, {@code false} otherwise.
	 */
	public boolean isReserved(LocalDate startDay, LocalDate endDay) {
		return (!(this.endDay.isBefore(startDay)) || this.startDay.isAfter(endDay));
	}

	@Override
	public String toString() {
		return String.format("Reservation{id=%d, carId=%d, userId=%s, startDay=%s, endDay=%s", id, carId, userId, startDay, endDay);
	}
}