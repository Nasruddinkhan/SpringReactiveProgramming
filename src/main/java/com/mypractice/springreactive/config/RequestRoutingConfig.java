/**
 * nasru
 * RequestRoutingConfig.java
 * Feb 4, 2020
 */
package com.mypractice.springreactive.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.mypractice.springreactive.handler.RequestHandlerFunction;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

/**
 * @author nasru
 *
 */
@Configuration
public class RequestRoutingConfig {
	@Bean
	public RouterFunction<ServerResponse> route(RequestHandlerFunction requestHandlerFunction) {
		return RouterFunctions
				.route(GET("/funtional/flux").and(accept(MediaType.APPLICATION_JSON)), requestHandlerFunction::flux)
				.andRoute(GET("/funtional/mono").and(accept(MediaType.APPLICATION_JSON)), requestHandlerFunction::mono);
	}
}
