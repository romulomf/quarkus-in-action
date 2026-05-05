package org.acme.reservation;

import java.time.LocalDate;

import org.acme.reservation.reservation.Reservation;
import org.acme.reservation.reservation.ReservationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
class ReservationRepositoryTest {

	@Inject
	ReservationRepository repository;

	@Test
	void testCreateReservation() {
		Reservation reservation = new Reservation();
		reservation.startDay = LocalDate.now().plusDays(5);
		reservation.endDay = LocalDate.now().plusDays(12);
		reservation.carId = 384L;
		repository.save(reservation);
		Assertions.assertNotNull(reservation.id);
		Assertions.assertTrue(repository.findAll().contains(reservation));
	}
}