package br.com.vr.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vr.dto.CartaoDTO;
import br.com.vr.service.CartaoService;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {

	@Autowired
	private CartaoService cartaoService;
	
	@GetMapping
	public List<CartaoDTO> listar() {
		return cartaoService.listar();
	}
	
	@PostMapping
	public ResponseEntity<CartaoDTO> incluir(@RequestBody CartaoDTO cartaoDTO) {
		cartaoService.incluir(cartaoDTO);
		return new ResponseEntity<>(cartaoDTO, HttpStatus.CREATED);
	}
	
	@GetMapping("/{numeroCartao}")
	public ResponseEntity<BigDecimal> obterSaldo(@PathVariable("numeroCartao") Long numeroCartao) {
		BigDecimal saldo = cartaoService.obterPorNumero(numeroCartao).getSaldo();
		return new ResponseEntity<>(saldo, HttpStatus.OK);
	}
}
