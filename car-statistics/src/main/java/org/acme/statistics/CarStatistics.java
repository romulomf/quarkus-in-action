package org.acme.statistics;

import java.time.Instant;

import org.acme.statistics.inventory.GraphQLInventoryClient;

import io.quarkus.funqy.Funq;
import io.smallrye.mutiny.Uni;

public class CarStatistics {

	private final GraphQLInventoryClient inventoryClient;

	public CarStatistics(GraphQLInventoryClient inventoryClient) {
		this.inventoryClient = inventoryClient;
	}

	@Funq
	public Uni<String> getCarStatistics() {
		return inventoryClient.allCars()
				.map(cars -> "The Car Rental car statistics created at %s. Number of available cars: %d".formatted(Instant.now(), cars.size()));
	}
}