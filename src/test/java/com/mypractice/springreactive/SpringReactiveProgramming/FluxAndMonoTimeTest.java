/**
 * nasru
 * FluxAndMonoTimeTest.java
 * Dec 5, 2019
 */
package com.mypractice.springreactive.SpringReactiveProgramming;

import java.time.Duration;

import org.junit.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * @author nasru
 *
 */
public class FluxAndMonoTimeTest {
	@Test
	public void infiniteTest() throws InterruptedException {
		Flux<Long> infiniteFlux =Flux.interval(Duration.ofMillis(200))
				.log();
		infiniteFlux
			.subscribe((e)-> System.out.println("value is : "+e));
		Thread.sleep(2000);
	}
	@Test
	public void infiniteSeqTest()  {
		Flux<Long> infiniteFlux =Flux.interval(Duration.ofMillis(100))
				.take(3)
				.log();
		StepVerifier.create(infiniteFlux)
					.expectSubscription()
					.expectNext(0L, 1L,2L)
					.verifyComplete();
	}
	@Test
	public void infiniteSeqMapTest()  {
		Flux<Integer> infiniteFlux =Flux.interval(Duration.ofMillis(100))
				.map(m -> new Integer(m.intValue()))
				.take(3)
				.log();
		StepVerifier.create(infiniteFlux)
					.expectSubscription()
					.expectNext(0,1,2)
					.verifyComplete();
	}
	@Test
	public void infiniteSeqMapDelayTest()  {
		Flux<Integer> infiniteFlux =Flux.interval(Duration.ofMillis(100))
				.delayElements(Duration.ofSeconds(1))
				.map(m -> new Integer(m.intValue()))
				.take(3)
				.log();
		StepVerifier.create(infiniteFlux)
					.expectSubscription()
					.expectNext(0,1,2)
					.verifyComplete();
	}
}
