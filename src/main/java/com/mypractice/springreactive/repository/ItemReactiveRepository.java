/**
 * nasru
 * ItemReactiveRepository.java
 * Mar 18, 2020
 */
package com.mypractice.springreactive.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.mypractice.springreactive.document.Item;

import reactor.core.publisher.Flux;

/**
 * @author nasru
 *
 */
@Repository
public interface ItemReactiveRepository extends ReactiveMongoRepository<Item, String> {

	/**
	 * @param string
	 */
	Flux<Item> findBydescription(String description);

}
