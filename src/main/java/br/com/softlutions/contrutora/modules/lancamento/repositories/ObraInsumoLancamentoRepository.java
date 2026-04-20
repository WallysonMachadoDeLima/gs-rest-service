package br.com.softlutions.contrutora.modules.lancamento.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.softlutions.contrutora.modules.lancamento.entities.ObraInsumoLancamento;

@Repository
public interface ObraInsumoLancamentoRepository extends JpaRepository<ObraInsumoLancamento, Integer> {
    List<ObraInsumoLancamento> findByObraId(Integer obraId);
    List<ObraInsumoLancamento> findByInsumoId(Integer insumoId);
    List<ObraInsumoLancamento> findByFornecedorId(Integer fornecedorId);
}
