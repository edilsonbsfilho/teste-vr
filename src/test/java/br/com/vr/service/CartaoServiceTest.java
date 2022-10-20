package br.com.vr.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.vr.builders.CartaoBuilder;
import br.com.vr.builders.CartaoDTOBuilder;
import br.com.vr.domain.Cartao;
import br.com.vr.dto.CartaoDTO;
import br.com.vr.exception.CartaoExistenteException;
import br.com.vr.exception.CartaoInexistenteException;
import br.com.vr.exception.CartaoSaldoInsuficienteException;
import br.com.vr.exception.CartaoSenhaInvalidaException;
import br.com.vr.repository.CartaoRepository;

@ExtendWith(SpringExtension.class)
public class CartaoServiceTest {

	private static final long NUMERO_CARTAO_DEFAULT = 1L;
	private static final double SALDO_INICIAL = 500.00D;

	@InjectMocks
	private CartaoService cartaoService;
	
	@Mock
	private CartaoRepository cartaoRepository;
	
	@Mock
	private ModelMapper modelMapper;
	
	@Mock
	private PasswordEncoder passwordEncoder;
	
	@Test
	public void obterPorNumero() {
		Cartao cartao = CartaoBuilder.build();
		
		when(cartaoRepository.findByNumero(anyLong())).thenReturn(Optional.of(cartao));
		when(modelMapper.map(cartao, CartaoDTO.class)).thenReturn(CartaoDTOBuilder.build());
		
		CartaoDTO cartaoDTO = cartaoService.obterPorNumero(NUMERO_CARTAO_DEFAULT);
		
		assertNotNull(cartaoDTO);
	}
	
	@Test
	public void obterPorNumero_CartaoInexistente() {
		when(cartaoRepository.findByNumero(anyLong())).thenReturn(Optional.empty());
		
		CartaoInexistenteException cartaoInexistenteException = 
				assertThrows(CartaoInexistenteException.class, () -> cartaoService.obterPorNumero(NUMERO_CARTAO_DEFAULT));
		
		assertEquals("CARTAO_INEXISTENTE", cartaoInexistenteException.getMessage());
	}
	
	@Test
	public void incluir() {
		Cartao cartao = CartaoBuilder.build();
		when(cartaoRepository.findByNumero(anyLong())).thenReturn(Optional.empty());
		when(cartaoRepository.save(any(Cartao.class))).thenReturn(cartao);
		when(modelMapper.map(cartao, CartaoDTO.class)).thenReturn(CartaoDTOBuilder.build());
		when(passwordEncoder.encode(anyString())).thenReturn("1234");
		
		CartaoDTO cartaoDTO = cartaoService.incluir(CartaoDTOBuilder.build());
		
		assertEquals(cartaoDTO.getSaldo(), BigDecimal.valueOf(SALDO_INICIAL));
		assertEquals(cartaoDTO.getNumero(), NUMERO_CARTAO_DEFAULT);
	}
	
	@Test
	public void incluir_CartaoExistente() {
		Cartao cartao = CartaoBuilder.build();
		
		when(cartaoRepository.findByNumero(anyLong())).thenReturn(Optional.of(cartao));
		
		CartaoDTO cartaoDTO = CartaoDTOBuilder.build();
		
		CartaoExistenteException cartaoExistenteException = 
				assertThrows(CartaoExistenteException.class, () -> cartaoService.incluir(cartaoDTO));
		
		assertEquals(cartaoDTO.getNumero(), cartaoExistenteException.getCartao().getNumero());
	}
	
	@Test
	public void debitar() {
		Cartao cartao = CartaoBuilder.build();
		
		when(cartaoRepository.findByNumero(anyLong())).thenReturn(Optional.of(cartao));
		
		cartaoService.debitar(CartaoDTOBuilder.build());
		verify(cartaoRepository, atLeastOnce()).save(any(Cartao.class));
	}
	
	@Test
	public void debitar_CartaoInexistente() {
		when(cartaoRepository.findByNumero(anyLong())).thenReturn(Optional.empty());
		
		CartaoInexistenteException cartaoInexistenteException = 
				assertThrows(CartaoInexistenteException.class, () -> cartaoService.debitar(CartaoDTOBuilder.build()));
		
		assertEquals("CARTAO_INEXISTENTE", cartaoInexistenteException.getMessage());		
	}
	
	@Test
	public void validarPorNumeroESaldo() {
		when(cartaoRepository.findByNumeroAndSaldoGreaterThanEqual(anyLong(), any(BigDecimal.class)))
				.thenReturn(Optional.empty());	
		
		CartaoSaldoInsuficienteException cartaoSaldoInsuficienteException = 
				assertThrows(CartaoSaldoInsuficienteException.class, () -> cartaoService.validarPorNumeroESaldo(NUMERO_CARTAO_DEFAULT, BigDecimal.valueOf(1000.00D)));
		
		assertEquals("SALDO_INSUFICIENTE", cartaoSaldoInsuficienteException.getMessage());
	}
	
	@Test
	public void validarSenha() {
		when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);
				
		CartaoSenhaInvalidaException cartaoSenhaInvalidaException = 
				assertThrows(CartaoSenhaInvalidaException.class, () -> cartaoService.validarSenha("1234", "zdfgzdfhdfh"));
		
		assertEquals("SENHA_INVALIDA", cartaoSenhaInvalidaException.getMessage());
	}
}
