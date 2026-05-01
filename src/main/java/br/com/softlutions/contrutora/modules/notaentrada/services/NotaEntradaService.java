package br.com.softlutions.contrutora.modules.notaentrada.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.softlutions.contrutora.modules.lancamento.entities.ObraInsumoLancamento;
import br.com.softlutions.contrutora.modules.lancamento.entities.OrigemCompra;
import br.com.softlutions.contrutora.modules.lancamento.entities.TipoLancamento;
import br.com.softlutions.contrutora.modules.lancamento.repositories.ObraInsumoLancamentoRepository;
import br.com.softlutions.contrutora.modules.notaentrada.entities.NotaEntrada;
import br.com.softlutions.contrutora.modules.notaentrada.entities.NotaEntradaItem;
import br.com.softlutions.contrutora.modules.notaentrada.entities.NotaStatus;
import br.com.softlutions.contrutora.modules.notaentrada.repositories.NotaEntradaItemRepository;
import br.com.softlutions.contrutora.modules.notaentrada.repositories.NotaEntradaRepository;

@Service
public class NotaEntradaService {
    @Autowired
    private NotaEntradaRepository notaEntradaRepository;

    @Autowired
    private NotaEntradaItemRepository notaEntradaItemRepository;

    @Autowired
    private ObraInsumoLancamentoRepository lancamentoRepository;

    public List<NotaEntrada> findAll() {
        return notaEntradaRepository.findAll();
    }

    public Optional<NotaEntrada> findOne(@NonNull Integer id) {
        return notaEntradaRepository.findById(id);
    }

    public List<NotaEntradaItem> findItens(@NonNull Integer notaEntradaId) {
        return notaEntradaItemRepository.findByNotaEntradaId(notaEntradaId);
    }

    @Transactional
    public NotaEntrada create(@NonNull NotaEntrada nota, List<NotaEntradaItem> itens) {
        nota.setStatus(NotaStatus.RASCUNHO);
        if (nota.getValorTotal() == null && itens != null) {
            BigDecimal total = itens.stream()
                .map(i -> i.getValorTotal() != null ? i.getValorTotal() : BigDecimal.ZERO)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
            nota.setValorTotal(total);
        }
        NotaEntrada saved = notaEntradaRepository.save(nota);
        if (itens != null) {
            for (NotaEntradaItem item : itens) {
                item.setNotaEntradaId(saved.getId());
                notaEntradaItemRepository.save(item);
            }
        }
        return saved;
    }

    @Transactional
    public Optional<NotaEntrada> update(@NonNull Integer id, @NonNull NotaEntrada nota, List<NotaEntradaItem> itens) {
        return notaEntradaRepository.findById(id)
                .map(existing -> {
                    if (existing.getStatus() != NotaStatus.RASCUNHO) {
                        throw new IllegalStateException("Nota confirmada não pode ser editada");
                    }
                    existing.setObraId(nota.getObraId());
                    existing.setFornecedorId(nota.getFornecedorId());
                    existing.setNumeroNota(nota.getNumeroNota());
                    existing.setData(nota.getData());
                    existing.setObservacao(nota.getObservacao());
                    existing.setAtivo(nota.getAtivo());

                    if (itens != null) {
                        notaEntradaItemRepository.deleteAll(notaEntradaItemRepository.findByNotaEntradaId(id));
                        BigDecimal total = BigDecimal.ZERO;
                        for (NotaEntradaItem item : itens) {
                            item.setNotaEntradaId(id);
                            notaEntradaItemRepository.save(item);
                            total = total.add(item.getValorTotal() != null ? item.getValorTotal() : BigDecimal.ZERO);
                        }
                        existing.setValorTotal(total);
                    }
                    return notaEntradaRepository.save(existing);
                });
    }

    @Transactional
    public boolean delete(@NonNull Integer id) {
        Optional<NotaEntrada> opt = notaEntradaRepository.findById(id);
        if (opt.isPresent()) {
            NotaEntrada nota = opt.get();
            if (nota.getStatus() != NotaStatus.RASCUNHO) {
                throw new IllegalStateException("Nota confirmada não pode ser excluída");
            }
            notaEntradaItemRepository.deleteAll(notaEntradaItemRepository.findByNotaEntradaId(id));
            notaEntradaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public NotaEntrada confirmar(@NonNull Integer id) {
        NotaEntrada nota = notaEntradaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nota não encontrada: " + id));
        if (nota.getStatus() != NotaStatus.RASCUNHO) {
            throw new IllegalStateException("Nota já confirmada");
        }

        List<NotaEntradaItem> itens = notaEntradaItemRepository.findByNotaEntradaId(id);
        for (NotaEntradaItem item : itens) {
            ObraInsumoLancamento lancamento = new ObraInsumoLancamento();
            lancamento.setObraId(nota.getObraId());
            lancamento.setInsumoId(item.getInsumoId());
            lancamento.setFornecedorId(nota.getFornecedorId());
            lancamento.setTipoLancamento(TipoLancamento.COMPRA);
            lancamento.setOrigemCompra(OrigemCompra.NOTA);
            lancamento.setNotaEntradaId(nota.getId());
            lancamento.setData(nota.getData().atStartOfDay());
            lancamento.setQuantidade(item.getQuantidade());
            lancamento.setValorUnitario(item.getValorUnitario());
            lancamento.setValorTotal(item.getValorTotal());
            lancamento.setAtivo(true);
            lancamentoRepository.save(lancamento);
        }

        nota.setStatus(NotaStatus.CONFIRMADA);
        return notaEntradaRepository.save(nota);
    }
}
