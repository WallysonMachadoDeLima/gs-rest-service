package br.com.softlutions.contrutora.modules.notaentrada.entities;

import br.com.seuprojeto.enums.DbEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum NotaStatus implements DbEnum<Integer> {
    RASCUNHO(1, "Rascunho"),
    CONFIRMADA(2, "Confirmada");

    private final Integer id;
    private final String nome;
    private final boolean deprecated;

    NotaStatus(Integer id, String nome) {
        this(id, nome, false);
    }

    NotaStatus(Integer id, String nome, boolean deprecated) {
        this.id = id;
        this.nome = nome;
        this.deprecated = deprecated;
    }

    @Override
    public Integer getId() { return id; }

    @Override
    public String getNome() { return nome; }

    @Override
    public boolean isDeprecated() { return deprecated; }

    @JsonValue
    public String toJson() { return name(); }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static NotaStatus fromJson(String value) {
        for (NotaStatus s : values()) {
            if (s.name().equalsIgnoreCase(value) || s.getNome().equalsIgnoreCase(value)) return s;
        }
        throw new IllegalArgumentException("NotaStatus inválido: " + value);
    }

    public static NotaStatus fromId(Integer id) {
        for (NotaStatus s : values()) {
            if (s.getId().equals(id)) return s;
        }
        throw new IllegalArgumentException("NotaStatus id inválido: " + id);
    }
}
