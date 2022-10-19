package br.com.vr.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TransacaoDTO implements Serializable {

	private static final long serialVersionUID = 7742149025760527348L;
	
	@JsonProperty("numeroCartao")
	private Long numero;
	
	@JsonProperty("senhaCartao")
	private String senha;
	
	@JsonProperty("valor")
	private BigDecimal valor;

}
