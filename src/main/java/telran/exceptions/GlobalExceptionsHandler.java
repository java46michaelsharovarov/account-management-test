package telran.exceptions;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionsHandler {
	
	static Logger LOG = LoggerFactory.getLogger(GlobalExceptionsHandler.class);

	@ExceptionHandler(MethodArgumentNotValidException.class)
	ResponseEntity<String> handlerMethodArgument(MethodArgumentNotValidException e) {
		List<ObjectError> errors = e.getAllErrors();
		String body = errors.stream().map(err -> err.getDefaultMessage()).collect(Collectors.joining(";"));
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	ResponseEntity<String> handlerConstraintViolation(ConstraintViolationException e) {
		Set<ConstraintViolation<?>> constraints = e.getConstraintViolations();
		String body = constraints.stream().map(constraint -> constraint.getMessage()).collect(Collectors.joining(";"));
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
	
}
