package br.com.time11.dtos;

import javax.validation.constraints.NotNull;

import br.com.time11.entities.Dependente;
import br.com.time11.entities.Titular;
import br.com.time11.repositories.TitularRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DependenteDto {

	@NotNull(message="Nome do dependente não pode ser nullo")
	private String first_name;
	
	@NotNull
	private String last_name;
	
	@NotNull
	private String phone_number;
	
	@NotNull
	private String email;
	
	private Integer idTitular;
	
	public Dependente toEntity(TitularRepository titularRepository) {
		Dependente dependente =  Dependente.builder()
				.email(email)
				.nome(first_name)
				.sobrenome(last_name)
				.telefone(phone_number)
				.saldo(0.0)
				.build();
		
		Titular titular = titularRepository.findById(idTitular).get();
		dependente.setTitular(titular);
		
		return dependente;
	}	
}
