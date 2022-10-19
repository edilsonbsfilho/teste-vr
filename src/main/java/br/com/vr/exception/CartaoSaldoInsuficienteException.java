package br.com.vr.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
public class CartaoSaldoInsuficienteException extends RuntimeException {

	private static final String MSG_SALDO_INSUFICIENTE = "SALDO_INSUFICIENTE";
	private static final long serialVersionUID = -3266138941333746495L;

	public CartaoSaldoInsuficienteException() {
		super(MSG_SALDO_INSUFICIENTE);
	}
}
