package br.com.vr.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
public class TransacaoCartaoInexistenteException extends CartaoInexistenteException {

	private static final long serialVersionUID = 5044072156572374697L;	
}
