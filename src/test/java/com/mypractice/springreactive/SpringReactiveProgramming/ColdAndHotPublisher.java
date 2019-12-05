/**
 * nasru
 * ColdAndHotPublisher.java
 * Dec 6, 2019
 */
package com.mypractice.springreactive.SpringReactiveProgramming;

import java.time.Duration;

import org.junit.Test;

import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

/**
 * @author nasru
 *
 */
public class ColdAndHotPublisher {
	@Test
	public void coldPublisherTest() throws InterruptedException {
		Flux<String> flux = Flux.just("A","B","C","D","E","F").delayElements(Duration.ofSeconds(1));
		flux.subscribe(s->System.out.println("sucriber 1 ::"+s));
		Thread.sleep(2000);
		flux.subscribe(s->System.out.println("sucriber 2 ::"+s));
		Thread.sleep(4000);
	}
	@Test
	public void hotPublisherTest() throws InterruptedException {
		Flux<String> flux = Flux.just("A","B","C","D","E","F").delayElements(Duration.ofSeconds(1));
		ConnectableFlux<String> connectableFlux= flux.publish();
		connectableFlux.connect();
		connectableFlux.subscribe(s->System.out.println("sucriber 1 ::"+s));
		Thread.sleep(2000);
		connectableFlux.subscribe(s->System.out.println("sucriber 2 ::"+s));
		Thread.sleep(4000);
	}
}
