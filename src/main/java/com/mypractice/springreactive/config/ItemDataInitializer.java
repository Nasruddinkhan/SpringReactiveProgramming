/**
 * nasru
 * ItemDataInitializer.java
 * Mar 20, 2020
 */
package com.mypractice.springreactive.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.mypractice.springreactive.document.Item;
import com.mypractice.springreactive.repository.ItemReactiveRepository;

import reactor.core.publisher.Flux;

/**
 * @author nasru
 *
 */
@Component
public class ItemDataInitializer implements CommandLineRunner {
@Autowired
private ItemReactiveRepository itemReactiveRepository;
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		//initialData();
	}

	/**
	 * 
	 */
	public List<Item> data(){
		return Arrays.asList( new Item("SFK", "VIVO", 50000.00), 
				 new Item(null, "MI", 50000.00),
				 new Item(null, "SAMSUNG", 50000.00),
				 new Item(null, "APPLE", 50000.00));
	}
	private void initialData() {
		// TODO Auto-generated method stub
		itemReactiveRepository.deleteAll().thenMany(Flux.fromIterable(data())
				.flatMap(itemReactiveRepository::save)).thenMany(itemReactiveRepository.findAll())
		.subscribe(item->{
			System.out.println("ItemDataInitializer.initialData() ::"+ item);
		});
	}
}
