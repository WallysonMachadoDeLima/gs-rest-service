
package br.com.softlutions.contrutora.modules.colaborador.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import br.com.seuprojeto.enums.DbEnum;

public enum Profissao implements DbEnum<Integer> {
    SERVENTE(1, "Servente"),
    PEDREIRO(2, "Pedreiro"),
    PINTOR(3, "Pintor"),
    ELETRICISTA(4, "Eletricista"),
    ENCANADOR(5, "Encanador"),
    CARPINTEIRO(6, "Carpinteiro"),
    ARMADOR(7, "Armador"),
    GESSEIRO(8, "Gesseiro"),
    AZULEJISTA(9, "Azulejista"),
    MESTRE_DE_OBRAS(10, "Mestre de Obras");

    private final Integer id;
    private final String nome;
    private final boolean deprecated;

    Profissao(Integer id, String nome) {
        this(id, nome, false);
    }
    Profissao(Integer id, String nome, boolean deprecated) {
        this.id = id;
        this.nome = nome;
        this.deprecated = deprecated;
    }

    @Override
    public Integer getId() { return id; }
    @Override
    @JsonValue
    public String getNome() { return nome; }
    @Override
    public boolean isDeprecated() { return deprecated; }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static Profissao fromNome(String nome) {
        for (Profissao p : values()) {
            if (p.getNome().equalsIgnoreCase(nome)) return p;
        }
        throw new IllegalArgumentException("Profissao inválida: " + nome);
    }
    public static Profissao fromId(Integer id) {
        for (Profissao p : values()) {
            if (p.getId().equals(id)) return p;
        }
        throw new IllegalArgumentException("Profissao id inválido: " + id);
    }
}
