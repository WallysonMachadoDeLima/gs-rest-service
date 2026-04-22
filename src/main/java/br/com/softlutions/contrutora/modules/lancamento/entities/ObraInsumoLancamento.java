package br.com.softlutions.contrutora.modules.lancamento.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;

@Entity
public class ObraInsumoLancamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer obraId;
    private Integer insumoId;
    private Integer fornecedorId;
    private Integer prestadorColaboradorId;
    private Integer criadoPorColaboradorId;

    @jakarta.persistence.Column(name = "tipo_lancamento_id")
    @Schema(implementation = TipoLancamento.class)
    private TipoLancamento tipoLancamento;

    private LocalDateTime data;

    @Column(name = "criado_em", updatable = false)
    private LocalDateTime criadoEm;

    @PrePersist
    protected void onCreate() {
        if (criadoEm == null) criadoEm = LocalDateTime.now();
    }

    private BigDecimal quantidade;
    private BigDecimal valorUnitario;
    private BigDecimal valorTotal;
    private String observacao;
    private Boolean ativo = true;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getObraId() { return obraId; }
    public void setObraId(Integer obraId) { this.obraId = obraId; }

    public Integer getInsumoId() { return insumoId; }
    public void setInsumoId(Integer insumoId) { this.insumoId = insumoId; }

    public Integer getFornecedorId() { return fornecedorId; }
    public void setFornecedorId(Integer fornecedorId) { this.fornecedorId = fornecedorId; }

    public Integer getPrestadorColaboradorId() { return prestadorColaboradorId; }
    public void setPrestadorColaboradorId(Integer prestadorColaboradorId) { this.prestadorColaboradorId = prestadorColaboradorId; }

    public Integer getCriadoPorColaboradorId() { return criadoPorColaboradorId; }
    public void setCriadoPorColaboradorId(Integer criadoPorColaboradorId) { this.criadoPorColaboradorId = criadoPorColaboradorId; }

    public TipoLancamento getTipoLancamento() { return tipoLancamento; }
    public void setTipoLancamento(TipoLancamento tipoLancamento) { this.tipoLancamento = tipoLancamento; }

    public LocalDateTime getData() { return data; }
    public void setData(LocalDateTime data) { this.data = data; }

    public BigDecimal getQuantidade() { return quantidade; }
    public void setQuantidade(BigDecimal quantidade) { this.quantidade = quantidade; }

    public BigDecimal getValorUnitario() { return valorUnitario; }
    public void setValorUnitario(BigDecimal valorUnitario) { this.valorUnitario = valorUnitario; }

    public BigDecimal getValorTotal() { return valorTotal; }
    public void setValorTotal(BigDecimal valorTotal) { this.valorTotal = valorTotal; }

    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = observacao; }

    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }

    public LocalDateTime getCriadoEm() { return criadoEm; }
    public void setCriadoEm(LocalDateTime criadoEm) { this.criadoEm = criadoEm; }
}
