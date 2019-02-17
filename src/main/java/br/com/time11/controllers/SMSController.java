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

import br.com.time11.dtos.SMSDto;
import br.com.time11.services.SMSService;

@RestController
@RequestMapping("/sms")
public class SMSController 
{
	@Autowired
	SMSService smsService;
	
	@PostMapping()
	public ResponseEntity<?> sendSms(@Valid @RequestBody SMSDto dto) throws IOException, IOException{
		return new ResponseEntity<>(smsService.sendSms(dto), HttpStatus.CREATED);
	}
}
