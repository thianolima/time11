package br.com.time11.entities;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.time11.enums.Ativo;
import br.com.time11.enums.Categoria;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
public class Controle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@ManyToOne
    @JoinColumn(name = "id_controle_depentede", nullable = false, foreignKey = @ForeignKey(name = "fk_controle_dependente"))
	Dependente dependente;
	
	@Enumerated
	Categoria categoria;
	
	@Enumerated
	Ativo ativo;
}
