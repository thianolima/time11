package br.com.time11.entities;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Movimento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	Date dataHora;
	
	@ManyToOne
    @JoinColumn(name = "idestabelecimento", nullable = false, foreignKey = @ForeignKey(name = "fk_movimento_estabelecimento"))
	Estabelecimento estabelecimento;
	
	@ManyToOne
    @JoinColumn(name = "iddependente", nullable = false, foreignKey = @ForeignKey(name = "fk_movimento_dependente"))
	Dependente dependente;
	
	Double valor;
}
