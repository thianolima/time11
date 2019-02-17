package br.com.time11.dtos;

import javax.validation.constraints.NotNull;

import br.com.time11.entities.Estabelecimento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SellerDto 
{
	@NotNull(message="Nome da empresa não pode ser nullo")
	private String business_name;
	
	@NotNull(message="Telefone da empresa não pode ser nullo")
	private String business_phone;
	
	@NotNull(message="Nome da empresa não pode ser nullo")
	private String business_email;
	
	@NotNull(message="Website da empresa não pode ser nullo")
	private String business_website;
	
	@NotNull(message="Descrição não pode ser nullo")
	private String business_description;
	
	@NotNull(message="A categoria da empresa não pode ser nullo")
	private String mcc;

	@NotNull(message="Não pode ser nullo")
	private String ein;
		
	public Estabelecimento toEntity() {
		return Estabelecimento.builder()
				.nome(business_name)
				.telefone(business_phone)
				.email(business_email)
				.website(business_website)
				.descricao(business_description)
				.build();
	}
}
