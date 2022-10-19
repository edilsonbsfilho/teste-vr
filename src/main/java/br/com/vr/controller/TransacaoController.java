package br.com.vr.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vr.dto.TransacaoDTO;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

	private static final String TRANSACAO_OK = "OK";

	@PostMapping
	public ResponseEntity<String> processar(@RequestBody TransacaoDTO transacaoDTO) {
		return new ResponseEntity<String>(TRANSACAO_OK, HttpStatus.CREATED);
	}
}
