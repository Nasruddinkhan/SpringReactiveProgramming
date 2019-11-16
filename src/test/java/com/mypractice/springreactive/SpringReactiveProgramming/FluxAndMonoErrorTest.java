/**
 * nasru
 * FluxAndMonoErrorTest.java
 * Nov 16, 2019
 */
package com.mypractice.springreactive.SpringReactiveProgramming;

import org.junit.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * @author nasru
 *
 */
public class FluxAndMonoErrorTest {

	@Test
	public void fluxErrorTest() {
		Flux<String> sFlux	= Flux.just("Q","W","E")
							.concatWith(Flux.error(new RuntimeException("Exception occur")))
							.concatWith(Flux.just("D"))
							.onErrorResume((e)->{
								System.out.println("Exception : "+ e);
								return Flux.just("defult", "defult1");
							});
		StepVerifier.create(sFlux.log())
					.expectSubscription()
					.expectNext("Q","W","E")
					.expectError(RuntimeException.class)
					.verify();
	}

}
