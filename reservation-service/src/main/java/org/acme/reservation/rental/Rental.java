package org.acme.reservation.rental;

import java.time.LocalDate;

public class Rental {

	private final String id;

	private final String userId;

	private final Long reservationId;

	private final LocalDate starDate;

	public Rental(String id, String userId, Long reservationId, LocalDate startDate) {
		super();
		this.id = id;
		this.userId = userId;
		this.reservationId = reservationId;
		this.starDate = startDate;
	}

	public String getId() {
		return id;
	}

	public String getUserId() {
		return userId;
	}

	public Long getReservationId() {
		return reservationId;
	}

	public LocalDate getStartDate() {
		return starDate;
	}

	@Override
	public String toString() {
		return String.format("Rental{id=%s, userId=%s, reservationId=%d, startDate=%s}", id, userId, reservationId, starDate);
	}
}