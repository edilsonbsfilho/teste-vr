package br.com.vr.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.vr.dto.CartaoDTO;
import br.com.vr.dto.TransacaoDTO;
import br.com.vr.exception.CartaoInexistenteException;
import br.com.vr.exception.TransacaoCartaoInexistenteException;

@Service
public class TransacaoService {
	
	@Autowired
	private CartaoService cartaoService;

	@Transactional
	public void autorizar(TransacaoDTO transacaoDTO) {
		
		CartaoDTO cartaoDTO;
		
		try {
			cartaoDTO = cartaoService.obterPorNumero(transacaoDTO.getNumero());
		} catch (CartaoInexistenteException e) {
			throw new TransacaoCartaoInexistenteException();
		}
		
		cartaoService.validarSenha(transacaoDTO.getSenha(), cartaoDTO.getSenha());
		cartaoService.validarPorNumeroESaldo(transacaoDTO.getNumero(), transacaoDTO.getValor());
		
		BigDecimal novoSaldo = cartaoDTO.getSaldo().subtract(transacaoDTO.getValor());
		cartaoDTO.setSaldo(novoSaldo);
		cartaoService.debitar(cartaoDTO);
	}
}
