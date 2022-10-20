package br.com.vr.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CartaoInexistenteException extends RuntimeException{

	private static final String MSG_CARTAO_INEXISTENTE = "CARTAO_INEXISTENTE";
	private static final long serialVersionUID = -4463726513435964089L;
	
	public CartaoInexistenteException() {
		super(MSG_CARTAO_INEXISTENTE);
	}

}
