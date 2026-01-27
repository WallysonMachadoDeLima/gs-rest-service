package br.com.softlutions.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import br.com.seuprojeto.enums.DbEnum;

public enum RegistroStatus implements DbEnum<Integer> {
    ATIVO(1, "Ativo"),
    INATIVO(2, "Inativo"),
    PENDENTE(3, "Pendente"),
    CANCELADO(4, "Cancelado");

    private final Integer id;
    private final String nome;
    private final boolean deprecated;

    RegistroStatus(Integer id, String nome) {
        this(id, nome, false);
    }
    RegistroStatus(Integer id, String nome, boolean deprecated) {
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
    public static RegistroStatus fromNome(String nome) {
        for (RegistroStatus s : values()) {
            if (s.getNome().equalsIgnoreCase(nome)) return s;
        }
        throw new IllegalArgumentException("RegistroStatus inválido: " + nome);
    }
    public static RegistroStatus fromId(Integer id) {
        for (RegistroStatus s : values()) {
            if (s.getId().equals(id)) return s;
        }
        throw new IllegalArgumentException("RegistroStatus id inválido: " + id);
    }
}