package com.shadov.test.resilience.domain;

import com.shadov.test.resilience.infrastructure.CircuitBreakers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NameConfiguration {
	@Bean
	NameService nameService(CircuitBreakers circuitBreakers) {
		return new NameService(circuitBreakers.find(circuitBreakers.getNameService()));
	}
}
