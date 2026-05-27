package org.acme.inventory.health;

import org.acme.inventory.repository.CarRepository;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

import io.smallrye.health.api.Wellness;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@Wellness
public class CarCountCheck implements HealthCheck {

	private CarRepository carRepository;

	@Inject
	public CarCountCheck(CarRepository carRepository) {
		this.carRepository = carRepository;
	}

	@Transactional
	@Override
	public HealthCheckResponse call() {
		long carsCount = carRepository.findAll().count();
		boolean wellnessStatus = carsCount > 0l;
		return HealthCheckResponse.builder()
				.name("car-count-check")
				.status(wellnessStatus)
				.withData("cars-count", carsCount)
				.build();
	}
}