package br.com.vr.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Cartao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private Long numero;
	
	@Column(nullable = false)
	private BigDecimal saldo;
	
	@Column(nullable = false)
	private LocalDate dataVencimento;
	
	@Column(nullable = false)
	private String senha;
	
	@Column(nullable = false)
	private Boolean bloqueado;
}
