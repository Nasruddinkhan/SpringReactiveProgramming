package com.mypractice.springreactive.SpringReactiveProgramming;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * @author Nasruddin Khan 
 * 29-Oct-2019 - 8:34:04 pm
 * MonoFluxFilterTest.java
 */

public class MonoFluxFilterTest {
	List<String> name = Arrays.asList("Nasruddin","Farukh","Rohan","Niraj","Sagar");

	@Test
	public void monoFluxFilterTest() {
	Flux<String> fiterName=	Flux.fromIterable(name).filter(n -> n.startsWith("N")).log();
		StepVerifier.create(fiterName)
		.expectNext("Nasruddin","Niraj")
		.verifyComplete();
	}
	
	@Test
	public void monoFluxFilterLengthTest() {
	Flux<String> fiterName=	Flux.fromIterable(name).filter(n -> n.length() <= 5).log();
		StepVerifier.create(fiterName)
		.expectNext("Rohan","Niraj","Sagar", "Nasruddin")
		.verifyComplete();
	}
}
