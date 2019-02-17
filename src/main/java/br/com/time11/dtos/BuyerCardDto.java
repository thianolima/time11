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
public class BuyerCardDto {

	@NotNull(message = "Token não pode ser nullo")	
	String token;
	
	@NotNull(message = "Customer não pode ser nullo")
	String customer;
}
