package br.com.softlutions.contrutora.modules.terreno.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.softlutions.contrutora.modules.terreno.entities.Terreno;

public interface TerrenoRepository extends JpaRepository<Terreno, Long> {
}
