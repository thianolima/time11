package br.com.time11.controllers;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.time11.dtos.TransactionDto;
import br.com.time11.enums.Categoria;
import br.com.time11.services.MovimentoService;

@RestController
@RequestMapping("/movimentacoes")
public class MovimentoController {

	@Value("${zoop.sellerid}")
	String sellerid;
	
	@Autowired
	MovimentoService service;	
	
	@GetMapping("/tokenTitular/{tokenTitular}/tokenDependente/{tokenDependente}/valor/{valor}/descricao/{descricao}")
	public ResponseEntity<?>  inserir(@PathVariable String tokenTitular, 
							          @PathVariable String tokenDependente,
							          @PathVariable Integer valor,
							          @PathVariable String descricao) throws JsonProcessingException, IOException{
		
		TransactionDto dto = TransactionDto.builder()
				.amount(valor)
				.currency("BRL")
				.description(descricao)
				.on_behalf_of(sellerid)
				.customer(tokenTitular)
				.payment_type("credit")
				.idZoopDependente(tokenDependente)
				.build();
		
		return new ResponseEntity<>(service.inserir(dto), HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping(params={"inicio", "fim", "categoria", "idDependente"})
	public ResponseEntity<?> listarMovimentos(
			@RequestParam Date inicio, 
			@RequestParam Date fim,
			@RequestParam Categoria categoria,
			@RequestParam Integer idDependente) {
		return new ResponseEntity<>(service.listarMovimento(inicio, fim, categoria, idDependente), HttpStatus.CREATED);
	}
	
	
	@GetMapping
	public ResponseEntity<?> listarMovimentos(){
		return new ResponseEntity<>(service.listar(), HttpStatus.CREATED);
	}
}
