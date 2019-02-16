package br.com.time11.dtos;

import javax.validation.constraints.NotNull;

import br.com.time11.entities.Titular;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BuyerDto {

	@NotNull
	private String first_name;
	
	@NotNull
	private String last_name;
	
	@NotNull	
	private String taxpayer_id;
	
	@NotNull
	private String phone_number;
	
	@NotNull
	private String email;
	
	public Titular toEntity() {
		return Titular.builder()
			.nome(first_name)
			.sobrenome(last_name)
			.cpf(taxpayer_id)
			.telefone(phone_number)
			.email(email)
			.build();
	}	
}
