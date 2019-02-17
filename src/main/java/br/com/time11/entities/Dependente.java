package br.com.time11.entities;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dependente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String nome;
	String sobrenome;
	Double saldo;
	String telefone;
	String email;
	String idzoop;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "idtitular", nullable = false, foreignKey = @ForeignKey(name = "fk_dependete_titular"))
	private Titular titular;
}
