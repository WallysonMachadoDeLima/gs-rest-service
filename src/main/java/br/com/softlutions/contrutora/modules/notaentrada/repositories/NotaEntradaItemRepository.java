package br.com.softlutions.contrutora.modules.notaentrada.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.softlutions.contrutora.modules.notaentrada.entities.NotaEntradaItem;

@Repository
public interface NotaEntradaItemRepository extends JpaRepository<NotaEntradaItem, Integer> {
    List<NotaEntradaItem> findByNotaEntradaId(Integer notaEntradaId);
}
