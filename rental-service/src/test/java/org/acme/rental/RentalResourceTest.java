package org.acme.rental;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.time.LocalDate;

import org.acme.rental.reservation.Reservation;
import org.acme.rental.reservation.ReservationClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.kafka.InjectKafkaCompanion;
import io.quarkus.test.kafka.KafkaCompanionResource;
import io.restassured.RestAssured;
import io.smallrye.reactive.messaging.kafka.companion.ConsumerTask;
import io.smallrye.reactive.messaging.kafka.companion.KafkaCompanion;

@QuarkusTest
@QuarkusTestResource(KafkaCompanionResource.class)
class RentalResourceTest {

	@InjectKafkaCompanion
	KafkaCompanion kafkaCompanion;

	@Test
	void testRentalProlongedInvoiceSend() {
		// stub the ReservationClient call
		Reservation reservation = new Reservation();
		reservation.endDay = LocalDate.now().minusDays(1l);
		ReservationClient mock = Mockito.mock(ReservationClient.class);
		Mockito.when(mock.getById(1l)).thenReturn(reservation);
		QuarkusMock.installMockForType(mock, ReservationClient.class, RestClient.LITERAL);
		// start new rental for reservation with id 1
		RestAssured.given().when().post("/rental/start/user123/1").then().statusCode(200);
		// end the rental with one prolonged day
		RestAssured.given().when().put("/rental/end/user123/1").then().statusCode(200).body("active", is(false), "endDate", is(LocalDate.now().toString()));
		// verify that message is sent to the invoiced-adjust Kafka topic
		ConsumerTask<String, String> invoiceAdjust = kafkaCompanion.consumeStrings().fromTopics("invoices-adjust", 1).awaitNextRecord(Duration.ofSeconds(10l));
		assertEquals(1, invoiceAdjust.count());
		assertTrue(invoiceAdjust.getFirstRecord().value().contains("\"price\":" + RentalResource.STANDARD_PRICE_FOR_PROLONGED_DAY));
	}
}