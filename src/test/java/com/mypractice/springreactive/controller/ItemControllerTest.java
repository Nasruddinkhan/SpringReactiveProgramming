/**
 * nasru
 * ItemControllerTest.java
 * Mar 20, 2020
 */
package com.mypractice.springreactive.controller;

import static com.mypractice.springreactive.cons.ItemConstant.GET_ALL_ITEM;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.mypractice.springreactive.document.Item;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * @author nasru
 *
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@DirtiesContext
@AutoConfigureWebTestClient
public class ItemControllerTest {
	@Autowired
	private WebTestClient webClient;

	@Test
	public void getAllItems() {
		webClient.get().uri(GET_ALL_ITEM).exchange().expectStatus().isOk().expectHeader()
				.contentType(MediaType.APPLICATION_JSON_UTF8).expectBodyList(Item.class).hasSize(4);
	}
	

	@Test
	public void getAllItemsApproch() {
		webClient.get().uri(GET_ALL_ITEM).exchange().expectStatus().isOk().expectHeader()
				.contentType(MediaType.APPLICATION_JSON_UTF8).expectBodyList(Item.class).hasSize(4)
				.consumeWith((res)->{
					res.getResponseBody().forEach(item->assertTrue(item.getId() != null));
				});
	}
	
	@Test
	public void getAllItemsApproch1() {
	Flux<Item> items=	webClient.get().uri(GET_ALL_ITEM).exchange().expectStatus().isOk().expectHeader()
				.contentType(MediaType.APPLICATION_JSON_UTF8).returnResult(Item.class).getResponseBody();
	StepVerifier.create(items.log("value from network :"))
				.expectSubscription()
				.expectNextCount(4)
				.verifyComplete();
	}
	@Test
	public void getItemById() {
	  webClient.get().uri(GET_ALL_ITEM.concat("/{id}"), "SFK")
		.exchange()
		.expectStatus()
		.isOk().expectBody().jsonPath("$.price", 100.0);	
		}
	@Test
	public void saveItem() {
		Item item = new Item(null, "LG",6000.0 );
		webClient.post().uri(GET_ALL_ITEM.concat("/{save}")).contentType(MediaType.APPLICATION_JSON_UTF8)
		.body(Mono.just(item),Item.class)
		.exchange().expectStatus().isCreated().expectBody().jsonPath("$.id").isNotEmpty();
	
	}
}
