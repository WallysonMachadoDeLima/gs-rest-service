package br.com.softlutions.contrutora.modules.fornecedor.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Fornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @jakarta.persistence.Column(name = "tipo_id")
    @Schema(implementation = TipoFornecedor.class)
    private TipoFornecedor tipo;

    private String nome;
    private String documento;
    private String telefone;
    private String enderecoTexto;
    private String observacao;
    private Boolean ativo = true;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public TipoFornecedor getTipo() { return tipo; }
    public void setTipo(TipoFornecedor tipo) { this.tipo = tipo; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDocumento() { return documento; }
    public void setDocumento(String documento) { this.documento = documento; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getEnderecoTexto() { return enderecoTexto; }
    public void setEnderecoTexto(String enderecoTexto) { this.enderecoTexto = enderecoTexto; }

    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = observacao; }

    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }
}
