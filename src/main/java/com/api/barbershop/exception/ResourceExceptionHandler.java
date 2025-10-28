package com.api.barbershop.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.api.barbershop.dto.ErrorResponseDTO;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ResourceExceptionHandler {
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorResponseDTO> handleNotFoundException(NotFoundException ex, HttpServletRequest request) {
		return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage(), request.getRequestURI());
	}

	@ExceptionHandler(NotChangedException.class)
	public ResponseEntity<ErrorResponseDTO> handleNotChangedException(NotChangedException ex,
			HttpServletRequest request) {
		return buildResponse(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage(), request.getRequestURI());
	}

	@ExceptionHandler(DefaultActiveImageException.class)
	public ResponseEntity<ErrorResponseDTO> handleDefaultActiveImageException(DefaultActiveImageException ex,
			HttpServletRequest request) {
		return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), request.getRequestURI());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(MethodArgumentNotValidException ex,
			HttpServletRequest request) {
		List<String> messages = ex.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage)
				.collect(Collectors.toList());

		String combinedMessage = String.join("; ", messages);

		return buildResponse(HttpStatus.BAD_REQUEST, combinedMessage, request.getRequestURI());
	}

	private ResponseEntity<ErrorResponseDTO> buildResponse(HttpStatus status, String message, String path) {
		ErrorResponseDTO error = new ErrorResponseDTO(Instant.now(), status.value(), status.getReasonPhrase(), message,
				path);
		return ResponseEntity.status(status).body(error);
	}
}
