/**
 * nasru
 * FluxAndMonoControllerTest.java
 * Dec 10, 2019
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
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * @author nasru
 *
 */
@RunWith(SpringRunner.class)
@WebFluxTest
public class FluxAndMonoControllerTest {
	@Autowired
	WebTestClient webTestClient;
	@Test
	public void flux() {
		Flux<Integer> flux= webTestClient.get().uri("/flux")
						   .accept(MediaType.APPLICATION_JSON_UTF8)
						   .exchange()
						   .expectStatus().isOk()
						   .returnResult(Integer.class)
						   .getResponseBody();
		
		StepVerifier.create(flux.log())
					.expectSubscription()
					.expectNext(1)
					.expectNext(2)
					.expectNext(3)
					.expectNext(4)
					.expectNext(5)
					.expectNext(6)
					.verifyComplete();
						   
	}
	
	@Test
	public void fluxApprocah2() {
		webTestClient.get().uri("/flux")
		//.accept(MediaType.APPLICATION_JSON_UTF8)
		.exchange()
		.expectStatus().isOk()
		//.expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
		.expectBodyList(Integer.class)
		.hasSize(6);
	}
	
	
	@Test
	public void fluxApprocah3() {
		List<Integer> lists= Arrays.asList(1,2,3,4,5,6);
		EntityExchangeResult<List<Integer>> entiExchangeResult=	webTestClient.get().uri("/flux")
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
				.expectBodyList(Integer.class)
				.returnResult();
		assertEquals(lists, entiExchangeResult.getResponseBody());
	}
	
	
	@Test
	public void fluxApprocah4() {
		List<Integer> lists= Arrays.asList(1,2,3,4,5,6);
			webTestClient.get().uri("/flux")
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
				.expectBodyList(Integer.class)
				.consumeWith((res)->{
					assertEquals(lists, res.getResponseBody());
				});
		//assertEquals(lists, entiExchangeResult.getResponseBody());
	}
	@Test
	public void fluxStreamApproach() {
		Flux<Long> flux= webTestClient.get().uri("/fluxstream")
				   .accept(MediaType.APPLICATION_STREAM_JSON)
				   .exchange()
				   .expectStatus().isOk()
				   .returnResult(Long.class)
				   .getResponseBody();
		
		StepVerifier.create(flux)
					.expectNext(0L)
					.expectNext(1L)
					.expectNext(2L)
					.thenCancel()
					.verify();
	}
	@Test
	public void monoApproach() {
		Integer no = new Integer(1);
		webTestClient.get().uri("/mono")
				   .accept(MediaType.APPLICATION_JSON_UTF8)
				   .exchange()
				   .expectStatus().isOk()
				   .expectBody(Integer.class)
				   .consumeWith((res)->{
					   assertEquals(no, res.getResponseBody()); 
				   });
	}
}
