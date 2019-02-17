package br.com.time11.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cartao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	Integer id;
	
	String tokenzoop;
	String idzoop;
	
	String numero;
	String titular;
	String anoVencimento;
	String mesVencimento;
}
