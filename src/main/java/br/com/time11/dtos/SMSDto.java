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
	@NotNull
	private String to;
	
	@NotNull
	private String message;
	
	@NotNull
	private String carrier;
}
