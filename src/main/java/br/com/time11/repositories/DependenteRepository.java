package br.com.time11.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.time11.entities.Dependente;

public interface DependenteRepository extends JpaRepository<Dependente, Integer>{

	@Query("select d.saldo from Dependente d where d.idzoop = :idzoop")
	Double pesquisarSaldo(@Param("idzoop") String idzoop);
	
	@Query("select d from Dependente d where d.idzoop = :idzoop")
	Dependente pesquisarIdZoop(@Param("idzoop") String idzoop);
}
