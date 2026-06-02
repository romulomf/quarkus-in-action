package org.acme.reservation;

import java.time.LocalDate;

import org.acme.reservation.entity.Reservation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.quarkus.test.hibernate.reactive.panache.TransactionalUniAsserter;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.vertx.RunOnVertxContext;

@QuarkusTest
class ReservationPersistenceTest {

	@Test
	@RunOnVertxContext
	void testCreateReservation(TransactionalUniAsserter asserter) {
		Reservation reservation = new Reservation();
		reservation.startDay = LocalDate.now().plusDays(5);
		reservation.endDay = LocalDate.now().plusDays(12);
		reservation.carId = 384L;
		asserter.<Reservation>assertThat(reservation::persist, r -> {
			Assertions.assertNotNull(r.id);
			asserter.putData("reservation.id", r.id);
		});
		/**
		 * Por mais que o SonarLint reclame, não dá para usar
		 * Reservation::count como ele sugere, por incompatibilidade
		 * dos métodos gerados pelo panache que se perde na hora de
		 * chamar Reservation::count. Usar essas experessões por algum
		 * motivo faz o Panache se perder e chamar a implementação base
		 * de PanacheEntityBase ao invés da classe que descende dela
		 * que neste caso é Reservation.
		 */
		asserter.assertEquals(() -> Reservation.count(), 1L);
		asserter.assertThat(() -> Reservation.<Reservation>findById(asserter.getData("reservation.id")), persistedReservation -> {
			Assertions.assertNotNull(persistedReservation);
			Assertions.assertEquals(reservation.carId, persistedReservation.carId);
		});
	}
}