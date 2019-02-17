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
public class TransactionDto {

	@NotNull(message="O total não pode ser nullo")
    Integer amount; //VALOR SEM PONTO
	
	@NotNull(message="O tipo da moeda não pode ser nulla")
    String currency; //BRL
	
	@NotNull(message="A descrição não pode ser nulla")
    String description; 
	
	@NotNull(message="O ID da Vendedora não pode ser nullo")
    String on_behalf_of; //SELLER ID
	
	@NotNull(message="O comprador não pode ser nullo")
    String customer; //BUYER_ID
	
	@NotNull(message="O tipo de pagamento não pode ser nullo")
    String payment_type; //CREDIT
	
	@NotNull(message="O ID do dependente não pode ser nulla")
    String idZoopDependente;
}
