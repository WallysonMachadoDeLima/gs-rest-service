package br.com.softlutions.contrutora.modules.obra.entities;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Obra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer terrenoId;
    private String nome;
    private LocalDate dataInicio;
    private LocalDate dataFimPrevista;
       
    //@Convert(converter = StatusObraIdConverter.class) // autoApply já cobre
    @jakarta.persistence.Column(name = "status_id")
    @Schema(implementation = StatusObra.class)
    private StatusObra status;
    
    private String observacao;
    private Boolean ativo = true;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getTerrenoId() { return terrenoId; }
    public void setTerrenoId(Integer terrenoId) { this.terrenoId = terrenoId; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public LocalDate getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }

    public LocalDate getDataFimPrevista() { return dataFimPrevista; }
    public void setDataFimPrevista(LocalDate dataFimPrevista) { this.dataFimPrevista = dataFimPrevista; }

    public StatusObra getStatus() { return status; }
    public void setStatus(StatusObra status) { this.status = status; }

    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = observacao; }

    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }
}
