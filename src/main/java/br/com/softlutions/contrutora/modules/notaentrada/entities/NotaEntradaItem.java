package br.com.softlutions.contrutora.modules.notaentrada.entities;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class NotaEntradaItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer notaEntradaId;
    private Integer insumoId;
    private BigDecimal quantidade;
    private BigDecimal valorUnitario;
    private BigDecimal valorTotal;
    private String observacao;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getNotaEntradaId() { return notaEntradaId; }
    public void setNotaEntradaId(Integer notaEntradaId) { this.notaEntradaId = notaEntradaId; }

    public Integer getInsumoId() { return insumoId; }
    public void setInsumoId(Integer insumoId) { this.insumoId = insumoId; }

    public BigDecimal getQuantidade() { return quantidade; }
    public void setQuantidade(BigDecimal quantidade) { this.quantidade = quantidade; }

    public BigDecimal getValorUnitario() { return valorUnitario; }
    public void setValorUnitario(BigDecimal valorUnitario) { this.valorUnitario = valorUnitario; }

    public BigDecimal getValorTotal() { return valorTotal; }
    public void setValorTotal(BigDecimal valorTotal) { this.valorTotal = valorTotal; }

    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = observacao; }
}
