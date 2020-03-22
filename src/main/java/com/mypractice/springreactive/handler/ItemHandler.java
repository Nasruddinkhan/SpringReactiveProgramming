/**
 * nasru
 * ItemHandler.java
 * Mar 21, 2020
 */
package com.mypractice.springreactive.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.mypractice.springreactive.document.Item;
import com.mypractice.springreactive.repository.ItemReactiveRepository;

import reactor.core.publisher.Mono;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

/**
 * @author nasru
 *
 */
@Component
public class ItemHandler {
	@Autowired
	ItemReactiveRepository itemReactiveRepository;
	static Mono<ServerResponse> notFound = ServerResponse.notFound().build();

	public Mono<ServerResponse> getAllItems(ServerRequest request) {
		System.out.println("ItemHandler.getAllItems()");
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(itemReactiveRepository.findAll(),
				Item.class);
	}

	public Mono<ServerResponse> getOneItem(ServerRequest request) {
		System.out.println("ItemHandler.getOneItem()");
		String id = request.pathVariable("id");
		Mono<Item> itemMono = itemReactiveRepository.findById(id);
		return itemMono
				.flatMap(item -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(fromObject(item)))
				.switchIfEmpty(notFound);
	}

	public Mono<ServerResponse> createItem(ServerRequest request) {
		System.out.println("ItemHandler.createItem()");
		Mono<Item> item = request.bodyToMono(Item.class);
		return item.flatMap(itm -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
				.body(itemReactiveRepository.save(itm), Item.class));
	}

	public Mono<ServerResponse> deleteOneItem(ServerRequest request) {
		System.out.println("ItemHandler.deleteOneItem()");
		String id = request.pathVariable("id");
		Mono<Void> deletedtem = itemReactiveRepository.deleteById(id);
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(deletedtem, Void.class);
	}

	public Mono<ServerResponse> updateItem(ServerRequest request) {
		System.out.println("ItemHandler.updateItem()");
		String id = request.pathVariable("id");
		Mono<Item> updatedItem = request.bodyToMono(Item.class).flatMap((itm) -> {
			Mono<Item> itemMono = itemReactiveRepository.findById(id).flatMap(currentItem -> {
				currentItem.setDescription(itm.getDescription());
				currentItem.setPrice(itm.price);
				return itemReactiveRepository.save(currentItem);
			});
			return itemMono;
		});
		return updatedItem
				.flatMap(item -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(fromObject(item)))
				.switchIfEmpty(notFound);
	}
	public Mono<ServerResponse> itemExpectionHandler(ServerRequest request){
		System.out.println("ItemHandler.itemExpectionHandler()");;
		return Mono.error( new RuntimeException("Runtime error occured@@@@@@@@@"));
	}
}
