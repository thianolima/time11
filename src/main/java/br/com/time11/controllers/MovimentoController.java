package br.com.time11.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.time11.dtos.TransactionDto;
import br.com.time11.services.MovimentoService;

@RestController
@RequestMapping("/movimentacoes")
public class MovimentoController {

	@Autowired
	MovimentoService service;
	
	@PostMapping("/{idZoopDependente}")
	public void inserir(@PathVariable String idZoopDependente, @RequestBody TransactionDto dto) throws JsonProcessingException, IOException{
		dto.setIdZoopDependente(idZoopDependente);
		service.inserir(dto);
	}
	
	/*
	@GetMapping
	public ResponseEntity<?> listarMovimentos(
			@DateTimeFormat(iso = ISO.DATE_TIME) @RequestParam LocalDateTime inicio, 
			@DateTimeFormat(iso = ISO.DATE_TIME) @RequestParam LocalDateTime fim,
			@RequestParam Categoria categoria,
			@RequestParam Integer idDependente) {
		return new ResponseEntity<>(service.listarMovimento(inicio, fim, categoria, idDependente), HttpStatus.CREATED);
	}
	*/
	
	@GetMapping
	public ResponseEntity<?> listarMovimentos(){
		return new ResponseEntity<>(service.listar(), HttpStatus.CREATED);
	}
}
