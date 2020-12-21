package com.sunday.exception;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ExceptionCatcher {

	@ExceptionHandler(ItemNotFoundException.class)
	public ResponseEntity<Map<String, String>> catcher(RuntimeException ex, WebRequest request) {
		return ResponseEntity.ok().body(Map.of("reason", "Item Not found"));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> urlPattern(Exception ex, WebRequest request) {
		return ResponseEntity.ok("Worng URL Entered!!👿👿👿");
	}

}
