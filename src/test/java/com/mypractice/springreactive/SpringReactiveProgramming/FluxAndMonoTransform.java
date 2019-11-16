package com.mypractice.springreactive.SpringReactiveProgramming;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.ParallelFlux;
import reactor.test.StepVerifier;
/**
 * @author Nasruddin Khan 
 * 29-Oct-2019 - 8:34:04 pm
 * FluxAndMonoTransform.java
 */

public class FluxAndMonoTransform {
	List<String> name = Arrays.asList("Nasruddin","Farukh","Rohan","Niraj","Sagar");

	@Test
	public void transformName() {
		Flux<String> fluxName=	Flux.fromIterable(name).map(c -> c.toUpperCase()).log();
		StepVerifier.create(fluxName).
		expectNext("NASRUDDIN","FARUKH","ROHAN","NIRAJ","SAGAR")
		.verifyComplete();
	}
	
	@Test
	public void transformNameAndLength() {
		Flux<String> fluxName=	Flux.fromIterable(name).filter(c-> c.length()>6).map(c -> c.toUpperCase()).log();
		StepVerifier.create(fluxName)
		.expectNextCount(1)
		//.expectNext("NASRUDDIN")
		.verifyComplete();
	}
	
	@Test
	public void transformNameUsingFlatMap() {
		Flux<String> fluxName=	Flux.fromIterable(name)
				.flatMap(s->{
					return Flux.fromIterable(convertToList(s));
				}).log();
		StepVerifier.create(fluxName)
		.expectNextCount(10)
		//.expectNext("NASRUDDIN")
		.verifyComplete();
	}
/**
 * @param s
 * @return
 */@Test
	public void transformNameUsingFlatMapDtls() {
		Flux<String> fluxName=	Flux.fromIterable(name).window(2)
				.flatMap((s)->s.map(this::convertToList).
						parallel()).flatMap(s-> Flux.fromIterable(s)).log();
		
		ParallelFlux<String> fluxName1=	Flux.fromIterable(name).window(2)
				.flatMap((s)->s.map(this::convertToList)).parallel().flatMap(s-> Flux.fromIterable(s)).log();
		
		StepVerifier.create(fluxName1)
		.expectNextCount(10)
		//.expectNext("NASRUDDIN")
		.verifyComplete();
	}
	
	
private List< String> convertToList(String s) {
	// TODO Auto-generated method stub
	try {
		Thread.sleep(1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return Arrays.asList(s, "khan");
}
}
