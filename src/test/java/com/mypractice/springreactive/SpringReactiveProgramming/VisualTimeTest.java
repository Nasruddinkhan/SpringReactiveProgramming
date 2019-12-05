/**
 * nasru
 * VisualTimeTest.java
 * Dec 6, 2019
 */
package com.mypractice.springreactive.SpringReactiveProgramming;

import java.time.Duration;

import org.junit.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.scheduler.VirtualTimeScheduler;

/**
 * @author nasru
 *
 */
public class VisualTimeTest {

	@Test
	public void withoutVisualTime() {
		Flux<Long> loFlux = Flux.interval(Duration.ofSeconds(1))
			.take(3);
		StepVerifier.create(loFlux.log())
		.expectSubscription()
		.expectNext(0L, 1L, 2L)
		.verifyComplete();
	}
	
	@Test
	public void withVirtualTime() {
		VirtualTimeScheduler.getOrSet();
		Flux<Long> loFlux = Flux.interval(Duration.ofSeconds(1))
			.take(3);
		StepVerifier.withVirtualTime(()->loFlux.log())
		.expectSubscription()
		.thenAwait(Duration.ofSeconds(3))
		.expectNext(0L, 1L, 2L)
		.verifyComplete();
	}
}
