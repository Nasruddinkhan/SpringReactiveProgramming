/**
 * nasru
 * RequestHandlerFunctionalTest.java
 * Feb 4, 2020
 */
package com.mypractice.springreactive.SpringReactiveProgramming;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * @author nasru
 *
 */
@RunWith(SpringRunner.class)
@WebFluxTest
public class RequestHandlerFunctionalTest {
	@Autowired
	WebTestClient webTestClient;
	@Test
	public void reuestHandlerFunctionl() {
		Flux<Integer> flux= webTestClient.get().uri("/funtional/flux")
				   .accept(MediaType.APPLICATION_JSON)
				   .exchange()
				   .expectStatus().isOk()
				   .returnResult(Integer.class)
				   .getResponseBody();

StepVerifier.create(flux.log())
			.expectSubscription()
			.expectNext(1)
			.expectNext(2)
			.expectNext(3)
			.verifyComplete();
	}
}
