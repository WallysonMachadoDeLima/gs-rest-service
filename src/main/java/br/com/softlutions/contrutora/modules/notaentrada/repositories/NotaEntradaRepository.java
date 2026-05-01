package br.com.softlutions.contrutora.modules.notaentrada.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.softlutions.contrutora.modules.notaentrada.entities.NotaEntrada;

@Repository
public interface NotaEntradaRepository extends JpaRepository<NotaEntrada, Integer> {
    List<NotaEntrada> findByObraId(Integer obraId);
}
