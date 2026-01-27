package br.com.softlutions.contrutora.modules.obra.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import br.com.seuprojeto.enums.DbEnum;
public enum StatusObra implements DbEnum<Integer> {
    PLANEJADA(1, "Planejada"),
    EM_ANDAMENTO(2, "Em andamento"),
    PAUSADA(3, "Pausada"),
    CONCLUIDA(4, "Concluída");

    private final Integer id;
    private final String nome;
    private final boolean deprecated;

    StatusObra(Integer id, String nome) {
        this(id, nome, false);
    }
    StatusObra(Integer id, String nome, boolean deprecated) {
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
    public static StatusObra fromNome(String nome) {
        for (StatusObra s : values()) {
            if (s.getNome().equalsIgnoreCase(nome)) return s;
        }
        throw new IllegalArgumentException("StatusObra inválido: " + nome);
    }
    public static StatusObra fromId(Integer id) {
        for (StatusObra s : values()) {
            if (s.getId().equals(id)) return s;
        }
        throw new IllegalArgumentException("StatusObra id inválido: " + id);
    }
}
