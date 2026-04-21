package br.com.softlutions.contrutora.modules.fornecedor.entities;

import br.com.seuprojeto.enums.DbEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoFornecedor implements DbEnum<Integer> {
    DISTRIBUIDOR(1, "Distribuidor"),
    PRESTADOR(2, "Prestador"),
    AMBOS(3, "Ambos");

    private final Integer id;
    private final String nome;
    private final boolean deprecated;

    TipoFornecedor(Integer id, String nome) {
        this(id, nome, false);
    }

    TipoFornecedor(Integer id, String nome, boolean deprecated) {
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
    public static TipoFornecedor fromJson(String value) {
        for (TipoFornecedor t : values()) {
            if (t.name().equalsIgnoreCase(value) || t.getNome().equalsIgnoreCase(value)) return t;
        }
        throw new IllegalArgumentException("TipoFornecedor inválido: " + value);
    }

    public static TipoFornecedor fromId(Integer id) {
        for (TipoFornecedor t : values()) {
            if (t.getId().equals(id)) return t;
        }
        throw new IllegalArgumentException("TipoFornecedor id inválido: " + id);
    }
}
