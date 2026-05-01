package br.com.softlutions.contrutora.modules.lancamento.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.softlutions.contrutora.modules.lancamento.entities.ObraInsumoLancamento;
import br.com.softlutions.contrutora.modules.lancamento.entities.TipoLancamento;

@Repository
public interface ObraInsumoLancamentoRepository extends JpaRepository<ObraInsumoLancamento, Integer> {
    List<ObraInsumoLancamento> findByObraId(Integer obraId);
    List<ObraInsumoLancamento> findByInsumoId(Integer insumoId);
    List<ObraInsumoLancamento> findByFornecedorId(Integer fornecedorId);

    @Query("SELECT l FROM ObraInsumoLancamento l WHERE " +
           "(:obraId IS NULL OR l.obraId = :obraId) AND " +
           "(:insumoId IS NULL OR l.insumoId = :insumoId) AND " +
           "(:tipoLancamento IS NULL OR l.tipoLancamento = :tipoLancamento) AND " +
           "(:dataInicio IS NULL OR l.data >= :dataInicio) AND " +
           "(:dataFim IS NULL OR l.data <= :dataFim)" +
           "ORDER BY l.data DESC")
    List<ObraInsumoLancamento> findAllFiltered(
        @Param("obraId") Integer obraId,
        @Param("insumoId") Integer insumoId,
        @Param("tipoLancamento") TipoLancamento tipoLancamento,
        @Param("dataInicio") LocalDateTime dataInicio,
        @Param("dataFim") LocalDateTime dataFim
    );
}
