package com.shadov.test.resilience.domain;

import com.shadov.test.resilience.model.QueryParams;
import com.shadov.test.resilience.utils.Sleeping;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.vavr.collection.List;
import io.vavr.control.Try;

public class NameService {
	private final CircuitBreaker circuitBreaker;

	public NameService(CircuitBreaker circuitBreaker) {
		this.circuitBreaker = circuitBreaker;
	}

	public Try<List<String>> findNames(QueryParams params) {
		return Try.of(() -> circuitedFindNames(params));
	}

	private List<String> circuitedFindNames(QueryParams params) {
		return circuitBreaker.executeSupplier(() -> fun(params));
	}

	private List<String> fun(QueryParams params) {
		Sleeping.ms(params.getSleep());

		if (Math.random() < params.getError())
			throw new IllegalArgumentException("Could not find names");

		return List.of("John", "Rebecca");
	}
}
