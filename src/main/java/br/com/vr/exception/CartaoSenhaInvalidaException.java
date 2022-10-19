package br.com.vr.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
public class CartaoSenhaInvalidaException extends RuntimeException {

	private static final String MSG_SENHA_INVALIDA = "SENHA_INVALIDA";
	private static final long serialVersionUID = 1294497555103449618L;
	
	public CartaoSenhaInvalidaException() {
		super(MSG_SENHA_INVALIDA);
	}

}
