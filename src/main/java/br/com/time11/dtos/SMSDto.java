package br.com.time11.dtos;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SMSDto
{
	@NotNull(message="O número do destinatário não pode ser nullo")
	private String to;
	
	@NotNull(message="A mensagem não pode ser nulla")
	private String message;
	
	@NotNull(message="A operadora não pode ser nulla")
	private String carrier;
}
