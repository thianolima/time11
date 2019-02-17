package br.com.time11.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.time11.entities.Titular;

public interface TitularRepository extends JpaRepository<Titular, Integer>{

	Titular findByEmail(String email);
	Titular findByDependentesEmail(String email);
}
