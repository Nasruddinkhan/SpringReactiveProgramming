/**
 * nasru
 * ItemReactiveRepositoryTest.java
 * Mar 18, 2020
 */
package com.mypractice.springreactive.repository;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mypractice.springreactive.document.Item;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * @author nasru
 *
 */
@DataMongoTest
@RunWith(SpringRunner.class)
public class ItemReactiveRepositoryTest {
	@Autowired
	ItemReactiveRepository itemReactiveRepository;
       
	@Before
	public void beforeRunning() {
		Item item = new Item();
		item.id=null;
		item.description="Applpe laptop";
		item.price=50000.00;
		Item item1= new Item();
		item1.id="MI";
		item1.description="MI laptop";
		item1.price=30000.00;
		List<Item> items=   Arrays.asList(item, item1);
		itemReactiveRepository.deleteAll().thenMany(Flux.fromIterable(items))
		.flatMap(itemReactiveRepository::save)
		.doOnNext((itm->{
			System.out.println("item is "+itm);
		})).blockLast();
	}
	@Test
	public void getAllItems() {
	StepVerifier.create(itemReactiveRepository.findAll())
				.expectSubscription()
				.expectNextCount(2)
				.verifyComplete();
	}
	@Test
	public void getItemByID() {
		StepVerifier.create(itemReactiveRepository.findById("MI"))
		.expectSubscription()
		.expectNextMatches(i->i.description.equalsIgnoreCase("MI laptop"))
		.verifyComplete();
	}
}
