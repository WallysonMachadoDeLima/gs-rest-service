package br.com.softlutions.contrutora.modules.obra.entities;

import java.time.LocalDate;

import br.com.softlutions.shared.RegistroStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Obra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private RegistroStatus statusObra;
    private Long terrenoId;
    private String nome;
    private LocalDate dataInicio;
    private LocalDate dataFimPrevista;
    
    @Enumerated(EnumType.STRING)
    private StatusObra status;
    
    private String observacao;
    private Boolean ativo = true;

    // Getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getTerrenoId() { return terrenoId; }
    public void setTerrenoId(Long terrenoId) { this.terrenoId = terrenoId; }

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
