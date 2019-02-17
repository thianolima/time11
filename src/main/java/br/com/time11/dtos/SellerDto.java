package br.com.time11.dtos;

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
	private String business_name;
	
	private String business_phone;
	
	private String business_email;
	
	private String business_website;
	
	private String business_description;
	
	private String mcc;

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
