package br.com.softlutions.contrutora.modules.obra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.softlutions.contrutora.modules.obra.entities.Obra;

public interface ObraRepository extends JpaRepository<Obra, Integer> {
}
