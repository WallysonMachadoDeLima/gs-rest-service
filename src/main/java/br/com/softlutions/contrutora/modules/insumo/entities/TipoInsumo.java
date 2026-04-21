package br.com.softlutions.contrutora.modules.insumo.entities;

import br.com.seuprojeto.enums.DbEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoInsumo implements DbEnum<Integer> {
    MATERIAL(1, "Material"),
    SERVICO(2, "Serviço");

    private final Integer id;
    private final String nome;
    private final boolean deprecated;

    TipoInsumo(Integer id, String nome) {
        this(id, nome, false);
    }

    TipoInsumo(Integer id, String nome, boolean deprecated) {
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
    public static TipoInsumo fromJson(String value) {
        for (TipoInsumo t : values()) {
            if (t.name().equalsIgnoreCase(value) || t.getNome().equalsIgnoreCase(value)) return t;
        }
        throw new IllegalArgumentException("TipoInsumo inválido: " + value);
    }

    public static TipoInsumo fromId(Integer id) {
        for (TipoInsumo t : values()) {
            if (t.getId().equals(id)) return t;
        }
        throw new IllegalArgumentException("TipoInsumo id inválido: " + id);
    }
}
