package br.com.vr.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.vr.dto.CartaoDTO;
import br.com.vr.exception.CartaoExistenteException;
import br.com.vr.exception.CartaoInexistenteException;

@RestController
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(CartaoExistenteException.class)
	public final ResponseEntity<CartaoDTO> handleCartaoExistenteExceptions(CartaoExistenteException exception, WebRequest webRequest) {
		return new ResponseEntity<CartaoDTO>(exception.getCartao(), HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@ExceptionHandler(CartaoInexistenteException.class)
	public final ResponseEntity<?> handleCartaoInexistenteExceptions(CartaoInexistenteException exception, WebRequest webRequest) {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
