package br.com.time11.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Titular {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String nome;
	String sobrenome;
	String cpf;
	String telefone;
	String email;
	String idzoop;
	
	@ManyToOne
    @JoinColumn(name = "idcartao", nullable = true, foreignKey = @ForeignKey(name = "fk_titular_cartao"))
	Cartao cartao;
	
    @OneToMany(mappedBy = "titular") 
    @OnDelete(action = OnDeleteAction.CASCADE)
	List<Dependente> dependentes;
}
