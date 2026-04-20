package br.com.softlutions.contrutora.modules.insumo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.softlutions.contrutora.modules.insumo.entities.Insumo;

@Repository
public interface InsumoRepository extends JpaRepository<Insumo, Integer> {
}
