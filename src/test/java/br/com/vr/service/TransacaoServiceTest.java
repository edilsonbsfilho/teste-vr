package br.com.vr.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.vr.builders.CartaoDTOBuilder;
import br.com.vr.builders.TransacaoDTOBuilder;
import br.com.vr.dto.CartaoDTO;
import br.com.vr.exception.CartaoInexistenteException;
import br.com.vr.exception.TransacaoCartaoInexistenteException;

@ExtendWith(SpringExtension.class)
public class TransacaoServiceTest {

	@InjectMocks
	private TransacaoService service;
	
	@Mock
	private CartaoService cartaoService;
	
	@Test
	public void autorizar() {
		when(cartaoService.obterPorNumero(anyLong())).thenReturn(CartaoDTOBuilder.build());
		
		service.autorizar(TransacaoDTOBuilder.build());
		
		verify(cartaoService, atLeastOnce()).debitar(any(CartaoDTO.class));
	}
	
	@Test
	public void autorizar_CartaoInexistente() {
		when(cartaoService.obterPorNumero(anyLong())).thenThrow(new CartaoInexistenteException());
		
		TransacaoCartaoInexistenteException transacaoCartaoInexistenteException = 
				assertThrows(TransacaoCartaoInexistenteException.class, () -> service.autorizar(TransacaoDTOBuilder.build()));
		
		assertEquals("CARTAO_INEXISTENTE", transacaoCartaoInexistenteException.getMessage());
	}
}
