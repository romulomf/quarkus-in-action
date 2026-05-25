package org.acme.rental.invoice;

import org.acme.rental.entity.Rental;
import org.acme.rental.invoice.data.InvoiceConfirmation;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class InvoiceConfirmationService {

	@Incoming("invoices-confirmations")
	public void invoicePaid(InvoiceConfirmation invoiceConfirmation) {
		Log.infof("Received invoice confirmation %s", invoiceConfirmation);
		if (!invoiceConfirmation.paid) {
			Log.warnf("Received unpaid invoice confirmation %s", invoiceConfirmation);
			// retry handling omitted
			InvoiceConfirmation.InvoiceReservation reservation = invoiceConfirmation.invoice.reservation;
			Rental.findByUserAndReservationIdsOptional(reservation.userId, reservation.id)
			.ifPresentOrElse(rental -> {
				// mark the already started rental as paid
				rental.paid = true;
				rental.update();
			}, () -> {
				// create new rental starting in the future
				Rental rental = new Rental();
				rental.userId = reservation.userId;
				rental.startDate = reservation.startDay;
				rental.active = false;
				rental.paid = true;
				rental.persist();
			});
		}
	}
}