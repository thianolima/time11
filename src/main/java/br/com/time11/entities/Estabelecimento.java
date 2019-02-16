package br.com.time11.entities;


import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.time11.enums.Categoria;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
public class Estabelecimento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String Nome;
	
	@Enumerated
	Categoria categoria;
}
