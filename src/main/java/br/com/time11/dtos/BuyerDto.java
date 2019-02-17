package br.com.time11.dtos;

import javax.validation.constraints.Email;
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
	
	@NotNull(message = "O nome não pode ser nullo")
	private String first_name;
	
	@NotNull(message = "O sobrenome não pode ser nullo")
	private String last_name;
	
	@NotNull(message = "O CPF/CNPJ não pode ser nullo")	
	private String taxpayer_id;
	
	@NotNull(message = "O Telefone não pode ser nullo")
	private String phone_number;
	
	@NotNull(message = "O E-mail não pode ser nullo")
	@Email(message = "E-mail inválido")
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
