/**
 * nasru
 * FunctionalGlobalErrorHandling.java
 * Mar 22, 2020
 */
package com.mypractice.springreactive.exception;

import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RequestPredicates.all;

import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * @author nasru
 *
 */
@Component
@Slf4j
public class FunctionalGlobalErrorHandling extends AbstractErrorWebExceptionHandler {
	/**
	 * @param errorAttributes
	 * @param resourceProperties
	 * @param applicationContext
	 */
	public FunctionalGlobalErrorHandling(ErrorAttributes errorAttributes, ApplicationContext applicationContext,
			ServerCodecConfigurer serverCodecConfigurer) {

		super(errorAttributes, new ResourceProperties(), applicationContext);
		// TODO Auto-generated constructor stub
		super.setMessageReaders(serverCodecConfigurer.getReaders());
		super.setMessageWriters(serverCodecConfigurer.getWriters());
	}

	@Override
	protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
		// TODO Auto-generated method stub
		return RouterFunctions.route(all(), this::renderErrorResponse) ;
	}
	public Mono<ServerResponse>  renderErrorResponse(ServerRequest request){
	Map<String, Object> errMap=	getErrorAttributes(request, false);
	log.info("errMap :"+errMap);	
	return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON)
			.body(BodyInserters.fromObject(errMap));
	}
}
