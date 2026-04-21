package br.com.softlutions.contrutora.modules.lancamento.entities;

import br.com.seuprojeto.enums.DbEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoLancamento implements DbEnum<Integer> {
    COMPRA(1, "Compra"),
    CONSUMO(2, "Consumo"),
    SERVICO(3, "Serviço"),
    AJUSTE(4, "Ajuste");

    private final Integer id;
    private final String nome;
    private final boolean deprecated;

    TipoLancamento(Integer id, String nome) {
        this(id, nome, false);
    }

    TipoLancamento(Integer id, String nome, boolean deprecated) {
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
    public static TipoLancamento fromJson(String value) {
        for (TipoLancamento t : values()) {
            if (t.name().equalsIgnoreCase(value) || t.getNome().equalsIgnoreCase(value)) return t;
        }
        throw new IllegalArgumentException("TipoLancamento inválido: " + value);
    }

    public static TipoLancamento fromId(Integer id) {
        for (TipoLancamento t : values()) {
            if (t.getId().equals(id)) return t;
        }
        throw new IllegalArgumentException("TipoLancamento id inválido: " + id);
    }
}
