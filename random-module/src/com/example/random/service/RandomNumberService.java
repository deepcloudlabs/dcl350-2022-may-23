package com.example.random.service;

import java.util.Optional;
import java.util.ServiceLoader;

public interface RandomNumberService {
	public static final ServiceLoader<RandomNumberService> services = ServiceLoader.load(RandomNumberService.class);

	int generate(int min, int max);

	default int generate(int max) {
		return generate(1, max);
	}

	public static Optional<RandomNumberService> getRandomNumberService(QualityLevel level) {
		for (var service : services) {
			var clazz = service.getClass();
			if (clazz.isAnnotationPresent(ServiceQuality.class)) {
				var serviceQuality = clazz.getDeclaredAnnotation(ServiceQuality.class);
				if (serviceQuality.value() == level)
					return Optional.of(service);
			}
		}
		return Optional.empty();
	}
}
