package br.com.vr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vr.dto.TransacaoDTO;
import br.com.vr.service.TransacaoService;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

	private static final String TRANSACAO_OK = "OK";
	
	@Autowired
	private TransacaoService transacaoService;

	@PostMapping
	public ResponseEntity<String> autorizar(@RequestBody TransacaoDTO transacaoDTO) {
		transacaoService.autorizar(transacaoDTO);
		return new ResponseEntity<>(TRANSACAO_OK, HttpStatus.CREATED);
	}
}
