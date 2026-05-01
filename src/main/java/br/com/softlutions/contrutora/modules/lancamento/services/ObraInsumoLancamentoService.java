package br.com.softlutions.contrutora.modules.lancamento.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import br.com.softlutions.contrutora.modules.lancamento.entities.ObraInsumoLancamento;
import br.com.softlutions.contrutora.modules.lancamento.entities.TipoLancamento;
import br.com.softlutions.contrutora.modules.lancamento.repositories.ObraInsumoLancamentoRepository;

@Service
public class ObraInsumoLancamentoService {
    @Autowired
    private ObraInsumoLancamentoRepository repository;

    public List<ObraInsumoLancamento> findAll() {
        return repository.findAll();
    }

    public Optional<ObraInsumoLancamento> findOne(@NonNull Integer id) {
        return repository.findById(id);
    }

    public List<ObraInsumoLancamento> findByObra(@NonNull Integer obraId) {
        return repository.findByObraId(obraId);
    }

    public List<ObraInsumoLancamento> findAllFiltered(
            Integer obraId,
            Integer insumoId,
            String tipoLancamento,
            LocalDate dataInicio,
            LocalDate dataFim
    ) {
        TipoLancamento tipo = null;
        if (tipoLancamento != null && !tipoLancamento.isBlank()) {
            tipo = TipoLancamento.valueOf(tipoLancamento);
        }
        LocalDateTime dtInicio = dataInicio != null ? dataInicio.atStartOfDay() : null;
        LocalDateTime dtFim = dataFim != null ? dataFim.atTime(LocalTime.MAX) : null;
        return repository.findAllFiltered(obraId, insumoId, tipo, dtInicio, dtFim);
    }

    public ObraInsumoLancamento create(@NonNull ObraInsumoLancamento lancamento) {
        if (lancamento.getValorTotal() == null && lancamento.getQuantidade() != null && lancamento.getValorUnitario() != null) {
            lancamento.setValorTotal(lancamento.getQuantidade().multiply(lancamento.getValorUnitario()));
        }
        return repository.save(lancamento);
    }

    public Optional<ObraInsumoLancamento> update(@NonNull Integer id, @NonNull ObraInsumoLancamento dados) {
        return repository.findById(id)
                .map(l -> {
                    l.setObraId(dados.getObraId());
                    l.setInsumoId(dados.getInsumoId());
                    l.setFornecedorId(dados.getFornecedorId());
                    l.setPrestadorColaboradorId(dados.getPrestadorColaboradorId());
                    l.setCriadoPorColaboradorId(dados.getCriadoPorColaboradorId());
                    l.setTipoLancamento(dados.getTipoLancamento());
                    l.setData(dados.getData());
                    l.setQuantidade(dados.getQuantidade());
                    l.setValorUnitario(dados.getValorUnitario());
                    if (dados.getValorTotal() != null) {
                        l.setValorTotal(dados.getValorTotal());
                    } else if (dados.getQuantidade() != null && dados.getValorUnitario() != null) {
                        l.setValorTotal(dados.getQuantidade().multiply(dados.getValorUnitario()));
                    }
                    l.setObservacao(dados.getObservacao());
                    l.setAtivo(dados.getAtivo());
                    return repository.save(l);
                });
    }

    public boolean delete(@NonNull Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
