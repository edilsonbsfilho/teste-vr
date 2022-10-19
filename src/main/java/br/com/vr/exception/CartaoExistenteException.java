package br.com.vr.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.vr.dto.CartaoDTO;

@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
public class CartaoExistenteException extends RuntimeException {

	private static final long serialVersionUID = -7266911300346523054L;
	
	private CartaoDTO cartao;
	
	public CartaoExistenteException(CartaoDTO cartao) {
		this.cartao = cartao;
	}

	public CartaoDTO getCartao() {
		return cartao;
	}

}
