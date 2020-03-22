/**
 * nasru
 * ItemRouter.java
 * Mar 21, 2020
 */
package com.mypractice.springreactive.config;

import static com.mypractice.springreactive.cons.ItemConstant.GET_ALL_FUN_ITEM;
import static com.mypractice.springreactive.cons.ItemConstant.GET_ID;
import static com.mypractice.springreactive.cons.ItemConstant.SAVE;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.mypractice.springreactive.handler.ItemHandler;

/**
 * @author nasru
 *
 */
@Configuration
public class ItemRouter {
	@Bean
	public RouterFunction<ServerResponse> itemRoute(ItemHandler itemHandler) {
		System.out.println("ItemRouter.itemRoute()");
		return RouterFunctions
				.route(GET(GET_ALL_FUN_ITEM).and(accept(MediaType.APPLICATION_JSON)), itemHandler::getAllItems)
				.andRoute(GET(GET_ALL_FUN_ITEM + GET_ID).and(accept(MediaType.APPLICATION_JSON)),
						itemHandler::getOneItem)
				.andRoute(POST(GET_ALL_FUN_ITEM + SAVE).and(accept(MediaType.APPLICATION_JSON)),
						itemHandler::createItem)
				.andRoute(DELETE(GET_ALL_FUN_ITEM + GET_ID).and(accept(MediaType.APPLICATION_JSON)),
						itemHandler::deleteOneItem)
				.andRoute(PUT(GET_ALL_FUN_ITEM + GET_ID).and(accept(MediaType.APPLICATION_JSON)),
						itemHandler::updateItem);
	}
}
