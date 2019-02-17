package br.com.time11.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import br.com.time11.entities.Movimento;
import br.com.time11.enums.Categoria;

public interface MovimentacaoRepository extends JpaRepository<Movimento, Integer>{
	/*
	@Query( "select m from Movimento m "
		  + " where m.dataHora between :inicio and :fim"
		  + " and m.estabelecimento.categoria = :categoria"
		  + " and m.dependente.id = :idDependente")
	public List<Movimento> listarMovimentos(@Param("inicio") Date inicio,
			                                @Param("fim") Date fim,
			                                @Param("categoria") Categoria categoria,
			                                @Param("idDependente") Integer idDependente);

*/
	
	public List<Movimento> findAllByDataHoraLessThanEqualAndDataHoraGreaterThanEqualAndEstabelecimentoCategoriaAndDependenteId(
											@Param("inicio") Date inicio,
			                                @Param("fim") Date fim,
			                                @Param("categoria") Categoria categoria,
			                                @Param("idDependente") Integer idDependente);
	
}
