package br.com.vr.builders;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.vr.dto.CartaoDTO;

public class CartaoDTOBuilder {

	public static CartaoDTO build() {
		CartaoDTO cartaoDTO = new CartaoDTO();
		cartaoDTO.setBloqueado(false);
		cartaoDTO.setDataVencimento(LocalDate.now().plusYears(3L));
		cartaoDTO.setId(1L);
		cartaoDTO.setNumero(1L);
		cartaoDTO.setSaldo(BigDecimal.valueOf(500.00D));
		cartaoDTO.setSenha("1234");
		return cartaoDTO;
	}
}
