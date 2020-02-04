package com.mypractice.springreactive.SpringReactiveProgramming;
/**
 * @author Nasruddin Khan 
 * 29-Oct-2019 - 8:34:04 pm
 * FluxAndMonoFactoryTest.java
 */

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import org.junit.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class FluxAndMonoFactoryTest {
	List<String> name = Arrays.asList("Nasruddin","Farukh","Rohan","Niraj","Sagar");
	@Test
	public void fluxUsingIterable() {
		Flux<String> fluxItr =	Flux.fromIterable(name).log();
		StepVerifier.create(fluxItr)
		.expectNext("Nasruddin","Farukh","Rohan","Niraj","Sagar")
		.verifyComplete();
	}
	
	@Test
	public void fluxUsingArrays() {
		String []name =new String[] {"Nasruddin","Farukh","Rohan","Niraj","Sagar"};
		Flux<String> fluxItr =	Flux.fromArray(name).log();
		StepVerifier.create(fluxItr)
		.expectNext("Nasruddin","Farukh","Rohan","Niraj","Sagar")
		.verifyComplete();
	}
	
	@Test
	public void fluxUsingStream() {
		Flux<String> fluxItr =	Flux.fromStream(name.stream()).log();
		StepVerifier.create(fluxItr)
		.expectNext("Nasruddin","Farukh","Rohan","Niraj","Sagar")
		.verifyComplete();
	}
	
	@Test
	public void monoJustEmpty() {
		Mono<String> monStr =	Mono.justOrEmpty(null);
		StepVerifier.create(monStr.log())
		.verifyComplete();
	}
	@Test
	public void monowithSuppler() {
		Supplier<Integer> num = () -> 12132132; 
		System.out.println(num.get());
		Mono<Integer> monoNum=	Mono.fromSupplier(num);
		StepVerifier.create(monoNum.log()).expectNext(12132132).verifyComplete();
	}
	@Test
	public void fluxUsingRange() {
		Flux<Integer>	fluxRange = Flux.range(1, 3).log();
		StepVerifier
		.create(fluxRange)
		.expectNext(1,2,3).verifyComplete();

	}
	
}
