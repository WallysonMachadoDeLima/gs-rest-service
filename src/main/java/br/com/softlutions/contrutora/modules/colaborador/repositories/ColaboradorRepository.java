package br.com.softlutions.contrutora.modules.colaborador.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.softlutions.contrutora.modules.colaborador.entities.Colaborador;

public interface ColaboradorRepository extends JpaRepository<Colaborador, Integer> {
}
