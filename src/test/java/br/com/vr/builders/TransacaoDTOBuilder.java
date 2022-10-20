package br.com.vr.builders;

import java.math.BigDecimal;

import br.com.vr.dto.TransacaoDTO;

public class TransacaoDTOBuilder {

	public static TransacaoDTO build() {
		TransacaoDTO dto = new TransacaoDTO();
		dto.setNumero(1L);
		dto.setSenha("1234");
		dto.setValor(BigDecimal.TEN);
		return dto;
	}
}
