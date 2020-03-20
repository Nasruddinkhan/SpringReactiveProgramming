/**
 * nasru
 * ItemController.java
 * Mar 20, 2020
 */
package com.mypractice.springreactive.controller;

import static com.mypractice.springreactive.cons.ItemConstant.GET_ALL_ITEM;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mypractice.springreactive.document.Item;
import com.mypractice.springreactive.repository.ItemReactiveRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * @author nasru
 *
 */
@RestController
@Slf4j
public class ItemController {
	@Autowired
	ItemReactiveRepository itemReactiveRepository;
	@GetMapping(GET_ALL_ITEM)
	public Flux<Item> findAllItems(){
		return 		itemReactiveRepository.findAll();
		
	}
}
