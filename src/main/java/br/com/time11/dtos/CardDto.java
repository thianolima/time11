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

	@NotNull
	String card_number;
	
	@NotNull
	String holder_name;
	
	@NotNull
	String expiration_year;
	
	@NotNull
	String security_code;
	
	@NotNull
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
