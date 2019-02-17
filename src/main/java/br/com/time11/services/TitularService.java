package br.com.time11.services;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import br.com.time11.dtos.BuyerCardDto;
import br.com.time11.dtos.BuyerDto;
import br.com.time11.dtos.CardDto;
import br.com.time11.dtos.DependenteDto;
import br.com.time11.entities.Cartao;
import br.com.time11.entities.Dependente;
import br.com.time11.entities.Titular;
import br.com.time11.repositories.CartaoRepository;
import br.com.time11.repositories.DependenteRepository;
import br.com.time11.repositories.TitularRepository;

@Service
public class TitularService {

	@Value("${zoop.markeplace_id}")
	String markeplace_id;
	
	@Value("${zoop.url_base}")
	String url_base;
	
	@Value("${zoop.api_published_key}")
	String api_published_key;
	
	@Autowired
	TitularRepository titularRepository;
	
	@Autowired
	CartaoRepository cartaoRespoistory;
		
	@Autowired
	DependenteRepository dependenteRepository;
	
	public Titular inserir(BuyerDto dto) throws JsonProcessingException, IOException {
			RestTemplate rest = new RestTemplate();	
			
			String url = url_base + "/v1/marketplaces/" + markeplace_id + "/buyers";
			
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(dto);
			
			RequestEntity<?> request = RequestEntity				
					.post(URI.create(url))
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.header("Authorization", api_published_key)
					.body(json);
			
			ResponseEntity<String> response = rest.exchange(request, String.class);
			json = response.getBody();
			
			HashMap<String,Object> result = new ObjectMapper().readValue(json, HashMap.class);
			
			Titular titular = dto.toEntity();
			titular.setIdzoop(result.get("id").toString());
			
			return titularRepository.save(titular);
	}
	
	public Titular inserirCartao(CardDto cardDto) throws JsonProcessingException, IOException  {
		RestTemplate rest = new RestTemplate();	
		
		String url = url_base + "/v1/marketplaces/" + markeplace_id + "/cards/tokens";
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(cardDto);
		
		RequestEntity<?> request = RequestEntity				
				.post(URI.create(url))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.header("Authorization", api_published_key)
				.body(json);
		
		ResponseEntity<String> response = rest.exchange(request, String.class);
		json = response.getBody();
		
		HashMap<String,Object> result = new ObjectMapper().readValue(json, HashMap.class);
		String idzoopCartao = (String) ((HashMap<String, Object>) result.get("card")).get("id");
		
		Cartao cartao = cardDto.toEntity();
		cartao.setTokenzoop(result.get("id").toString());
		cartao.setIdzoop(idzoopCartao);
		
		cartao = cartaoRespoistory.save(cartao);
		
		//-------------------------------------------------------------------------		
		// ASSOCIANDO O CARTAO AO TITULAR
		//-------------------------------------------------------------------------
		Titular titular = titularRepository.findById(cardDto.getIdTitular()).get();		
		
		url = url_base + "/v1/marketplaces/" + markeplace_id + "/cards";
		
		BuyerCardDto buyerCardDto = BuyerCardDto.builder()
				.customer(titular.getIdzoop())
				.token(cartao.getTokenzoop())
				.build();
		
		ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		json = ow.writeValueAsString(buyerCardDto);
		
		request = RequestEntity				
				.post(URI.create(url))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.header("Authorization", api_published_key)
				.body(json);
		
		response = rest.exchange(request, String.class);
		json = response.getBody();
				
		titular.setCartao(cartao);
		return titularRepository.save(titular);
	}
	
	public Dependente inserirDependente(DependenteDto dto) throws JsonProcessingException, IOException {
		RestTemplate rest = new RestTemplate();	
		
		String url = url_base + "/v1/marketplaces/" + markeplace_id + "/buyers";
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(dto);
		
		RequestEntity<?> request = RequestEntity				
				.post(URI.create(url))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.header("Authorization", api_published_key)
				.body(json);
		
		ResponseEntity<String> response = rest.exchange(request, String.class);
		json = response.getBody();
		
		HashMap<String,Object> result = new ObjectMapper().readValue(json, HashMap.class);
		
		Dependente dependente = dto.toEntity(titularRepository);
		dependente.setIdzoop(result.get("id").toString());
		
		return dependenteRepository.save(dependente);
	} 
	
	public Titular pesquisarEmail(String email) {
		return titularRepository.findByEmail(email);
	}
	
	public Titular pesquisarEmailDependente(String email) {
		return titularRepository.findByDependentesEmail(email);
	}
}
