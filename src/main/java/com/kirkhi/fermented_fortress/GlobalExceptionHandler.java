package com.kirkhi.fermented_fortress;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDto> handleGenericException(Exception e) {
		logger.error("Handle Generic Exception");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ErrorResponseDto("general Exception", e.getMessage(), LocalDateTime.now()));
	}
	
//	@ExceptionHandler(EntityNotFoundException.class)
//	public ResponseEntity<ErrorResponseDto> handleNotFoundException(EntityNotFoundException e) {
//		logger.error("Handle Not Found Exception");
//		
//		return ResponseEntity.status(HttpStatus.NOT_FOUND)
//				.body(new ErrorResponseDto("Entity Not Found", e.getMessage(), LocalDateTime.now()));
//	}
//	
//	@ExceptionHandler(exception = {
//			IllegalArgumentException.class,
//			IllegalStateException.class,
//			MethodArgumentNotValidException.class
//	})
//	public ResponseEntity<ErrorResponseDto> handleBadRequestException(EntityNotFoundException e) {
//		logger.error("Handle Bad Request Exception");
//		
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//				.body(new ErrorResponseDto("Bad Request", e.getMessage(), LocalDateTime.now()));
//	}
}
