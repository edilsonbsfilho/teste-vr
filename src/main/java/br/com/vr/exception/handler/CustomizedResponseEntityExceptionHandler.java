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
import br.com.vr.exception.CartaoSaldoInsuficienteException;
import br.com.vr.exception.CartaoSenhaInvalidaException;
import br.com.vr.exception.TransacaoCartaoInexistenteException;

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
	
	@ExceptionHandler(TransacaoCartaoInexistenteException.class)
	public final ResponseEntity<String> handleTransacaoCartaoInexistenteExceptions(TransacaoCartaoInexistenteException exception, WebRequest webRequest) {
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@ExceptionHandler(CartaoSenhaInvalidaException.class)
	public final ResponseEntity<String> handleCartaoSenhaInvalidaExceptions(CartaoSenhaInvalidaException exception, WebRequest webRequest) {
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@ExceptionHandler(CartaoSaldoInsuficienteException.class)
	public final ResponseEntity<String> handleCartaoSaldoInsuficienteExceptions(CartaoSaldoInsuficienteException exception, WebRequest webRequest) {
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
	}
}
