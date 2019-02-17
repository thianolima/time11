package br.com.time11.services;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;

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

import br.com.time11.dtos.SellerDto;
import br.com.time11.entities.Estabelecimento;
import br.com.time11.repositories.EstabelecimentoRepository;

@Service
public class EstabelecimentoService 
{
	@Value("${zoop.markeplace_id}")
	String markeplace_id;
	
	@Value("${zoop.url_base}")
	String url_base;
	
	@Value("${zoop.api_published_key}")
	String api_published_key;
	
	@Autowired
	EstabelecimentoRepository estabelecimentoRepository;
	
	@Transactional
	public Estabelecimento inserir(SellerDto dto) throws JsonProcessingException, IOException 
	{
		RestTemplate rest = new RestTemplate();	
		
		String url = url_base + "/v1/marketplaces/" + markeplace_id + "/sellers/businesses";
		
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
		
		Estabelecimento estabelecimento = dto.toEntity();
		
		return estabelecimentoRepository.save(estabelecimento);
	}
	
	public List<Estabelecimento> listar(){
		return estabelecimentoRepository.findAll();
	}
}
