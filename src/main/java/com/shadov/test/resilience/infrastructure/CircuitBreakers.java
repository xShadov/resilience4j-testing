package com.shadov.test.resilience.infrastructure;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("circuit-breakers")
@PropertySource("classpath:circuit-breakers.properties")
public class CircuitBreakers {
	private CircuitBreakerRegistry circuitBreakerRegistry;

	public CircuitBreakers(CircuitBreakerRegistry circuitBreakerRegistry) {
		this.circuitBreakerRegistry = circuitBreakerRegistry;
	}

	public CircuitBreaker find(NamedCircuitBreaker namedCircuitBreaker) {
		return circuitBreakerRegistry.circuitBreaker(namedCircuitBreaker.getName());
	}

	public static class NamedCircuitBreaker {
		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

	private NamedCircuitBreaker nameService;

	public NamedCircuitBreaker getNameService() {
		return nameService;
	}

	public void setNameService(NamedCircuitBreaker nameService) {
		this.nameService = nameService;
	}
}
