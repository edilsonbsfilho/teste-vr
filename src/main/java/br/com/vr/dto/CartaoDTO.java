package br.com.vr.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CartaoDTO implements Serializable {

	private static final long serialVersionUID = -2041372450405467539L;
	
	@JsonIgnore
	private Long id;

	@JsonProperty("numeroCartao")
	private Long numero;
	
	@JsonIgnore
	private BigDecimal saldo;
	
	@JsonIgnore
	private LocalDate dataVencimento;
	
	@JsonProperty("senha")
	private String senha;
	
	@JsonIgnore
	private Boolean bloqueado;
}
