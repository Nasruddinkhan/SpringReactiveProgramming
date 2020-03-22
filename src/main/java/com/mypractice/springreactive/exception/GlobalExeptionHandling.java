/**
 * nasru
 * GlobalExeptionHandling.java
 * Mar 22, 2020
 */
package com.mypractice.springreactive.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * @author nasru
 *
 */

@ControllerAdvice
@Slf4j
public class GlobalExeptionHandling {
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<String> handleRuntimeException(RuntimeException e){
		log.error("ItemController handleRuntimeException RuntimeException: {}"+e);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleRuntimeException(Exception e){
		log.error("ItemController handleRuntimeException Exception: {}"+e);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		
	}
}
