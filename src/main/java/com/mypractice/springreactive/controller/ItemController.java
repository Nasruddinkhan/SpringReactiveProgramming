/**
 * nasru
 * ItemController.java
 * Mar 20, 2020
 */
package com.mypractice.springreactive.controller;

import static com.mypractice.springreactive.cons.ItemConstant.GET_ALL_ITEM;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mypractice.springreactive.document.Item;
import com.mypractice.springreactive.repository.ItemReactiveRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author nasru
 *
 */
@RestController
public class ItemController {
	@Autowired
	ItemReactiveRepository itemReactiveRepository;
	@GetMapping(GET_ALL_ITEM)
	public Flux<Item> findAllItems(){
		return 		itemReactiveRepository.findAll();
	}
	@GetMapping(GET_ALL_ITEM+"/{id}")
	public Mono<ResponseEntity<Item>> getOneItem(@PathVariable String id){
		return itemReactiveRepository.findById(id).map((item)-> new ResponseEntity<>(item, HttpStatus.OK))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	@PostMapping(GET_ALL_ITEM+"/{save}")
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Item> createdItem(@RequestBody Item item){
		return itemReactiveRepository.save(item);
	}
	@DeleteMapping(GET_ALL_ITEM+"/{id}")
	public Mono<Void> deleteItem(@PathVariable String id){
		return itemReactiveRepository.deleteById(id);
	}
}
