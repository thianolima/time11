package br.com.time11.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDto {

    Integer amount; //VALOR SEM PONTO
    String currency; //BRL
    String description; 
    String on_behalf_of; //SELLER ID
    String customer; //BUYER_ID
    String payment_type; //CREDIT
    String idZoopDependente;
}
