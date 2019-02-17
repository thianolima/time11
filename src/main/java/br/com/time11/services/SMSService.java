package br.com.time11.services;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import br.com.time11.dtos.SMSDto;

@Service
public class SMSService 
{
	@Value("${wavy.url_base}")
	String wavy_url_base;
	
	@Value("${wavy.api.key}")
	String wavy_api_key;
	
	public String sendSms(SMSDto dto) throws JsonProcessingException
	{
		RestTemplate rest = new RestTemplate();	
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(dto);
		
		RequestEntity<?> request = RequestEntity				
				.post(URI.create(wavy_url_base))
				.contentType(MediaType.APPLICATION_JSON)
				.header("Access-key", wavy_api_key)
				.body(json);
		
		ResponseEntity<String> response = rest.exchange(request, String.class);
		json = response.getBody();
		
		
		return json.toString();
	}
}
