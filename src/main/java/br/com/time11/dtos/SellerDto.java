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
	@NotNull
	private String business_name;
	
	@NotNull
	private String business_phone;
	
	@NotNull
	private String business_email;
	
	@NotNull
	private String business_website;
	
	@NotNull
	private String business_description;
	
	@NotNull
	private String mcc;

	@NotNull 
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
