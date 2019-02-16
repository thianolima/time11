package br.com.time11.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.time11.entities.Cartao;

public interface CartaoRepository extends JpaRepository<Cartao, Long>{

}
