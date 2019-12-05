/**
 * nasru
 * FluxMonoBackPressureTest.java
 * Dec 6, 2019
 */
package com.mypractice.springreactive.SpringReactiveProgramming;

import org.junit.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * @author nasru
 *
 */
public class FluxMonoBackPressureTest {
	@Test
	public void backPresserTest() {
		Flux<Integer> publiser =	Flux.range(1, 10)
				.log();
		StepVerifier.create(publiser)
		.expectSubscription()
		.thenRequest(1)
		.expectNext(1)
		.thenRequest(1)
		.expectNext(2)
		.thenCancel()
		.verify();
	}
	@Test
	public void backPresserTest2() {
		Flux<Integer> publiser =	Flux.range(1, 10)
				.log();
		publiser.subscribe((e)->System.out.println("value is = "+e),
				(e1)->System.err.println("value is = "+e1),
				()->System.out.println("Done"),
				(subscription -> subscription.request(2)));
	}
	@Test
	public void backPresserCancelTest() {
		Flux<Integer> publiser =	Flux.range(1, 10)
				.log();
		publiser.subscribe((e)->System.out.println("value is = "+e),
				(e1)->System.err.println("value is = "+e1),
				()->System.out.println("Done"),
				(subscription -> subscription.cancel()));
	}
	@Test
	public void backPresserCustomize() {
		Flux<Integer> publiser =	Flux.range(1, 10)
				.log();
		publiser.subscribe(new BaseSubscriber<Integer>() {
			@Override
			protected void hookOnNext(Integer value) {
				request(1);
				System.out.println("Value recievied is : "+ value);
				if(value == 4)
					cancel();
			}
		});
	}
}
