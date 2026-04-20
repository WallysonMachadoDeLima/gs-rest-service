package br.com.softlutions.contrutora.modules.insumo.entities;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Insumo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @jakarta.persistence.Column(name = "tipo_id")
    @Schema(implementation = TipoInsumo.class)
    private TipoInsumo tipo;

    private String nome;

    @jakarta.persistence.Column(name = "categoria_id")
    @Schema(implementation = CategoriaInsumo.class)
    private CategoriaInsumo categoria;

    private Integer unidadeId;
    private Boolean controlaEstoque = true;
    private BigDecimal custoPadrao;
    private String descricao;
    private Boolean ativo = true;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public TipoInsumo getTipo() { return tipo; }
    public void setTipo(TipoInsumo tipo) { this.tipo = tipo; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public CategoriaInsumo getCategoria() { return categoria; }
    public void setCategoria(CategoriaInsumo categoria) { this.categoria = categoria; }

    public Integer getUnidadeId() { return unidadeId; }
    public void setUnidadeId(Integer unidadeId) { this.unidadeId = unidadeId; }

    public Boolean getControlaEstoque() { return controlaEstoque; }
    public void setControlaEstoque(Boolean controlaEstoque) { this.controlaEstoque = controlaEstoque; }

    public BigDecimal getCustoPadrao() { return custoPadrao; }
    public void setCustoPadrao(BigDecimal custoPadrao) { this.custoPadrao = custoPadrao; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }
}
