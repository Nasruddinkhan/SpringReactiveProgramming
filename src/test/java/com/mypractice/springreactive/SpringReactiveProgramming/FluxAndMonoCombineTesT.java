/**
 * nasru
 * FluxAndMonoCombineTesT.java
 * Nov 16, 2019
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
public class FluxAndMonoCombineTesT {
	@Test
	public void combineUsingMerger() {
		Flux<String> flux1 = Flux.just("N","B","C");
		Flux<String> flux2 = Flux.just("D","E","F");
		Flux<String> mergeFlux  =Flux.merge(flux1, flux2);
		StepVerifier.create(mergeFlux.log())
					.expectSubscription()
					.expectNext("N","B","C","D","E","F")
					.verifyComplete();
		
	}
	@Test
	public void combineUsingMergeWithDelay() {
		Flux<String> flux1 = Flux.just("N","B","C").delayElements(Duration.ofSeconds(1));
		Flux<String> flux2 = Flux.just("D","E","F").delayElements(Duration.ofSeconds(1));;
		Flux<String> mergeFlux  =Flux.merge(flux1, flux2);
		StepVerifier.create(mergeFlux.log())
					.expectSubscription()
					.expectNextCount(6)
					.verifyComplete();
		
	}
	@Test
	public void combineUsingConcat() {
		Flux<String> flux1 = Flux.just("N","B","C");
		Flux<String> flux2 = Flux.just("D","E","F");
		Flux<String> mergeFlux  =Flux.concat(flux1, flux2);
		StepVerifier.create(mergeFlux.log())
					.expectSubscription()
					.expectNextCount(6)
					.verifyComplete();
		
	}
	@Test
	public void combineUsingConcatWithDelay() {
		Flux<String> flux1 = Flux.just("N","B","C").delayElements(Duration.ofSeconds(1));
		Flux<String> flux2 = Flux.just("D","E","F").delayElements(Duration.ofSeconds(1));;
		Flux<String> mergeFlux  =Flux.concat(flux1, flux2);
		StepVerifier.create(mergeFlux.log())
					.expectSubscription()
					.expectNextCount(6)
					.verifyComplete();
		
	}
	@Test
	public void combineVirtualUsingConcatWithDelay() {
		VirtualTimeScheduler.getOrSet();
		Flux<String> flux1 = Flux.just("N","B","C").delayElements(Duration.ofSeconds(1));
		Flux<String> flux2 = Flux.just("D","E","F").delayElements(Duration.ofSeconds(1));;
		Flux<String> mergeFlux  =Flux.concat(flux1, flux2);
		StepVerifier.withVirtualTime(()->mergeFlux.log())
					.expectSubscription()
					.thenAwait(Duration.ofSeconds(6))
					.expectNextCount(6)
					.verifyComplete();
		
	}
	@Test
	public void combineUsingZip() {
		Flux<String> flux1 = Flux.just("N","B","C");
		Flux<String> flux2 = Flux.just("D","E","F");
		Flux<String> mergeFlux  =Flux.zip(flux1, flux2,(f1,f2)->{
			return f1.concat(f2);
		});
		StepVerifier.create(mergeFlux.log())
					.expectSubscription()
					.expectNext("ND","BE","CF")
					.verifyComplete();
		
	}
}
