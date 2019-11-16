package com.mypractice.springreactive.SpringReactiveProgramming;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
/**
 * @author Nasruddin Khan 
 * 29-Oct-2019 - 8:34:04 pm
 * FluxAndMonoTest.java
 */

public class FluxAndMonoTest {
	//private static final Logger log = LoggerFactory.getLogger(FluxAndMonoTest.class);
	@Test
	public void fluxTest() {
		Flux<String> flux =	Flux.just("Nasruddin", "sagar", "farcukh","Abrar")
				//.concatWith(Flux.error(new RuntimeException("error occur")))
				.concatWith(Flux.just("Last Name"))
				.log();
		flux
		.subscribe(System.out::println,
				(e)-> System.err.println("exception is"+e),
				()-> System.out.println("complete the string flux"));
	
		
	}
	@Test
	public void fluxTestWithError() {
		Flux<String> flux =	Flux.just("Nasruddin", "sagar", "farukh")
				.concatWith(Flux.error(new RuntimeException("error occur")))
				//.concatWith(Flux.just("Last Name"))
				.log();
		StepVerifier.create(flux)
		.expectNext("Nasruddin")
		.expectNext("sagar")
		.expectNext("farukh")
		//.expectError(RuntimeException.class)
		.expectErrorMessage("error occur")
		.verify();
	
		
	}
	
	@Test
	public void fluxTestWithError1() {
		Flux<String> flux =	Flux.just("Nasruddin", "sagar", "farukh")
				//.concatWith(Flux.error(new RuntimeException("error occur")));
				//.concatWith(Flux.just("Last Name"))
				.log();
		StepVerifier.create(flux)
		.expectNext("Nasruddin", "sagar", "farukh")
		.expectErrorMessage("error occur")
		.verify();
		
	}
	
	@Test
	public void monoTest() {
		Mono<String> mono=	Mono.just("Nasruddin");
		StepVerifier.create(mono.log()).expectNext("Nasruddin").verifyComplete();
	}
	
	@Test
	public void monoTestwithError() {
		StepVerifier.create(Mono.error(new RuntimeException("Mono Errors")).log()).
		expectNext("Nasruddin").
		expectError(RuntimeException.class).
		verify();
	}
	
	@Test
	public void testSetUp() {
		String str = "I am done with junit setup";
		assertEquals("I am done with junit", str);
	}
	
}
