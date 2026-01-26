package br.com.softlutions.contrutora.modules.terreno.entities;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Terreno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nomeApelido;
    private String enderecoTexto;
    private BigDecimal largura;
    private BigDecimal altura;
    private String matricula;
    private Boolean ativo = true;

    // Getters e setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNomeApelido() { return nomeApelido; }
    public void setNomeApelido(String nomeApelido) { this.nomeApelido = nomeApelido; }

    public String getEnderecoTexto() { return enderecoTexto; }
    public void setEnderecoTexto(String enderecoTexto) { this.enderecoTexto = enderecoTexto; }

    public BigDecimal getLargura() { return largura; }
    public void setLargura(BigDecimal largura) { this.largura = largura; }

    public BigDecimal getAltura() { return altura; }
    public void setAltura(BigDecimal altura) { this.altura = altura; }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }

    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }
}
