package br.com.vr.builders;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.vr.domain.Cartao;

public class CartaoBuilder {

	public static Cartao build() {
		Cartao cartao = new Cartao();
		cartao.setBloqueado(false);
		cartao.setDataVencimento(LocalDate.now().plusYears(3L));
		cartao.setId(1L);
		cartao.setNumero(1L);
		cartao.setSaldo(BigDecimal.valueOf(500.00D));
		cartao.setSenha("1234");
		return cartao;
	}
}
