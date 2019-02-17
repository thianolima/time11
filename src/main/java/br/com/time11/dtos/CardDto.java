package br.com.time11.dtos;

import javax.validation.constraints.NotNull;

import br.com.time11.entities.Cartao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardDto {

	@NotNull(message="Numero do cartão é obrigatório")
	String card_number;
	
	@NotNull(message="Nome do titular do cartão é obrigatório")
	String holder_name;
	
	@NotNull(message="Ano de expiração obrigatório")
	String expiration_year;
	
	String security_code;
	
	@NotNull(message="Mês de expiração obrigatório")
	String expiration_month;
	
	@NotNull
	Integer idTitular;
	
	public Cartao toEntity() {
		return Cartao.builder()
				.numero(card_number)
				.titular(holder_name)
				.anoVencimento(expiration_year)
				.mesVencimento(expiration_month)
				.numero(card_number)
				.build();
	}
}
