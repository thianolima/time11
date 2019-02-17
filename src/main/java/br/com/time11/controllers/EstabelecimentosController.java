package br.com.time11.controllers;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.time11.dtos.SellerDto;
import br.com.time11.services.EstabelecimentoService;

@RestController
@RequestMapping("/estabelecimentos")
public class EstabelecimentosController 
{
	
	@Autowired
	EstabelecimentoService service;
	
	@PostMapping
	public ResponseEntity<?> inserir(@Valid @RequestBody SellerDto dto) throws IOException, IOException{
		return new ResponseEntity<>(service.inserir(dto), HttpStatus.CREATED);
	}
}
