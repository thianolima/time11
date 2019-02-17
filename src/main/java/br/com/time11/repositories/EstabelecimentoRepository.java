package br.com.time11.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import br.com.time11.entities.Estabelecimento;

public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento, Integer>{

	Optional<Estabelecimento> findByIdzoop(@Param("idzoop") String idzoop);
}
