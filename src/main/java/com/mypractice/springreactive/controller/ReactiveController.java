/**
 * nasru
 * ReactiveController.java
 * Dec 10, 2019
 */
package com.mypractice.springreactive.controller;

import java.time.Duration;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author nasru
 *
 */
@RestController
public class ReactiveController {

	@GetMapping("/flux")
	public Flux<Integer> returnFlux() {
		return Flux.just(1, 2, 3, 4, 5, 6).log();
	}

	@GetMapping(value = "/fluxstream", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<Long> returnFluxStream() {
		return Flux.interval(Duration.ofSeconds(1)).log();
	}

	@GetMapping("/mono")
	public Mono<Integer> returnMono() {
		return Mono.just(1).log();
	}
	
	
}
