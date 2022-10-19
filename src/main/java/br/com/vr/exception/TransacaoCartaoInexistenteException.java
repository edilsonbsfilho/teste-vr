package br.com.vr.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
public class TransacaoCartaoInexistenteException extends RuntimeException {

	private static final String MSG_CARTAO_INEXISTENTE = "CARTAO_INEXISTENTE";
	private static final long serialVersionUID = 5044072156572374697L;
	
	public TransacaoCartaoInexistenteException() {
		super(MSG_CARTAO_INEXISTENTE);
	}

}
