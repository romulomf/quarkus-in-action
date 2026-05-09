package org.acme.reservation.reservation;

import java.time.LocalDate;

public class Reservation {

	public Long id;

	public Long carId;

	public LocalDate startDay;

	public LocalDate endDay;

	public String userId;

	/**
	 * Check if the given duration overlaps with this reservation.
	 * 
	 * @param startDay the reservation's start day
	 * @param endDay the reservation's end day
	 * 
	 * @return {@code true} if the dates overlap with the reservation, {@code false} otherwise.
	 */
	public boolean isReserved(LocalDate startDay, LocalDate endDay) {
		return (!(this.endDay.isBefore(startDay)) || this.startDay.isAfter(endDay));
	}
}