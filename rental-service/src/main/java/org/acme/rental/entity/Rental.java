package org.acme.rental.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

@MongoEntity(collection = "Rentals")
public class Rental extends PanacheMongoEntity {

	public String userId;

	public Long reservationId;

	public LocalDate startDate;

	public LocalDate endDate;

	public boolean active;

	public boolean paid;

	public Rental() {
		// construtor padrão
	}

	public Rental(String userId, Long reservationId, LocalDate startDate, LocalDate endDate, boolean active, boolean paid) {
		this();
		this.userId = userId;
		this.reservationId = reservationId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.active = active;
		this.paid = paid;
	}

	@Override
	public String toString() {
		return String.format("Rental{id=%s, userId=%s, reservationId=%d, startDate=%s, endDate=%s, active=%s, paid = %b}", id, userId, reservationId, startDate, endDate, active, paid);
	}

	public static Optional<Rental> findByUserAndReservationIdsOptional(String userId, Long reservationId) {
		return find("userId = ?1 and reservationId = ?2", userId, reservationId).firstResultOptional();
	}

	public static List<Rental> listActive() {
		return list("active", true);
	}
}