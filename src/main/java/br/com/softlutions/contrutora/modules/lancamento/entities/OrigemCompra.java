package br.com.softlutions.contrutora.modules.lancamento.entities;

import br.com.seuprojeto.enums.DbEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum OrigemCompra implements DbEnum<Integer> {
    NOTA(1, "Nota"),
    AVULSO(2, "Avulso");

    private final Integer id;
    private final String nome;
    private final boolean deprecated;

    OrigemCompra(Integer id, String nome) {
        this(id, nome, false);
    }

    OrigemCompra(Integer id, String nome, boolean deprecated) {
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
    public static OrigemCompra fromJson(String value) {
        for (OrigemCompra o : values()) {
            if (o.name().equalsIgnoreCase(value) || o.getNome().equalsIgnoreCase(value)) return o;
        }
        throw new IllegalArgumentException("OrigemCompra inválido: " + value);
    }

    public static OrigemCompra fromId(Integer id) {
        for (OrigemCompra o : values()) {
            if (o.getId().equals(id)) return o;
        }
        throw new IllegalArgumentException("OrigemCompra id inválido: " + id);
    }
}
