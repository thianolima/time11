package br.com.time11.controllers;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.time11.dtos.BuyerDto;
import br.com.time11.dtos.CardDto;
import br.com.time11.services.TitularService;

@RestController
@RequestMapping("/titulares")
public class TitularesController {

	@Autowired
	TitularService service;
	
	@PostMapping
	public ResponseEntity<?> inserir(@Valid @RequestBody BuyerDto dto) throws IOException, IOException{
		return new ResponseEntity<>(service.inserir(dto), HttpStatus.CREATED);
	}
	
	@PostMapping("/{idTitular}/cartoes")
	public ResponseEntity<?> inserirCartao(@Valid @PathVariable Integer idTitular, @RequestBody CardDto dto) throws IOException, IOException{
		dto.setIdTitular(idTitular);
		service.inserirCartao(dto);
		return new ResponseEntity<>("oi", HttpStatus.CREATED);
	}
	
}
