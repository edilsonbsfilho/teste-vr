package br.com.vr.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import br.com.vr.domain.Cartao;
import br.com.vr.dto.CartaoDTO;
import br.com.vr.exception.CartaoExistenteException;
import br.com.vr.exception.CartaoInexistenteException;
import br.com.vr.exception.CartaoSaldoInsuficienteException;
import br.com.vr.exception.CartaoSenhaInvalidaException;
import br.com.vr.repository.CartaoRepository;

@Service
public class CartaoService {

	private static final double SALDO_INICIAL = 500.00D;
	private static final int QUANTIDADE_ANOS_VALIDADE = 3;

	@Autowired
    private ModelMapper modelMapper;
	
	@Autowired
	private CartaoRepository cartaoRepository;
	
	public List<CartaoDTO> listar() {
		
		return cartaoRepository.findAll().stream()
				.map(c -> modelMapper.map(c, CartaoDTO.class))
				.toList();
	}
	
	public CartaoDTO obterPorNumero(Long numero) {
		try {
			Cartao cartao = cartaoRepository.findByNumero(numero).orElseThrow();
			return modelMapper.map(cartao, CartaoDTO.class);
		} catch (NoSuchElementException e) {
			throw new CartaoInexistenteException();
		} 
	}
	
	@Transactional
	public CartaoDTO incluir(CartaoDTO cartaoDTO) {
		
		try {
			cartaoRepository.findByNumero(cartaoDTO.getNumero()).orElseThrow();
			throw new CartaoExistenteException(cartaoDTO);
		} catch (NoSuchElementException e) {
			Cartao cartao = obterEntidadeCartao(cartaoDTO);		
			return modelMapper.map(cartaoRepository.save(cartao), CartaoDTO.class);
		}
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public void debitar(CartaoDTO cartaoDTO) {
		Cartao cartao = cartaoRepository.findByNumero(cartaoDTO.getNumero()).get();
		cartao.setSaldo(cartaoDTO.getSaldo());
		cartaoRepository.save(cartao);
	}
	
	private Cartao obterEntidadeCartao(CartaoDTO cartaoDTO) {
		Cartao cartao = new Cartao();
		cartao.setNumero(cartaoDTO.getNumero());
		cartao.setSenha(cartaoDTO.getSenha());
		cartao.setBloqueado(false);
		cartao.setDataVencimento(LocalDate.now().plusYears(QUANTIDADE_ANOS_VALIDADE));
		cartao.setSaldo(BigDecimal.valueOf(SALDO_INICIAL));
		return cartao;
	}

	public void obterPorNumeroESenha(Long numero, String senha) {
		try {
			cartaoRepository.findByNumeroAndSenha(numero, senha).orElseThrow();
		} catch (Exception e) {
			throw new CartaoSenhaInvalidaException();
		}
	}

	public void obterPorNumeroESaldo(Long numero, BigDecimal valor) {
		try {
			cartaoRepository.findByNumeroAndSaldoGreaterThanEqual(numero, valor).orElseThrow();
		} catch (Exception e) {
			throw new CartaoSaldoInsuficienteException();
		}
	}	
}
