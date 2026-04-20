package br.com.softlutions.contrutora.modules.insumo.entities;

import br.com.seuprojeto.enums.DbEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CategoriaInsumo implements DbEnum<Integer> {
    ALVENARIA(1, "Alvenaria"),
    CONCRETO(2, "Concreto"),
    ACABAMENTO(3, "Acabamento"),
    HIDRAULICA(4, "Hidráulica"),
    ELETRICA(5, "Elétrica"),
    PINTURA(6, "Pintura"),
    CARPINTARIA(7, "Carpintaria"),
    SERRALHERIA(8, "Serralheria"),
    MAO_DE_OBRA(9, "Mão de Obra"),
    TERRAPLENAGEM(10, "Terraplenagem"),
    OUTROS(99, "Outros");

    private final Integer id;
    private final String nome;
    private final boolean deprecated;

    CategoriaInsumo(Integer id, String nome) {
        this(id, nome, false);
    }

    CategoriaInsumo(Integer id, String nome, boolean deprecated) {
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
    public static CategoriaInsumo fromNome(String nome) {
        for (CategoriaInsumo c : values()) {
            if (c.getNome().equalsIgnoreCase(nome)) return c;
        }
        throw new IllegalArgumentException("CategoriaInsumo inválida: " + nome);
    }

    public static CategoriaInsumo fromId(Integer id) {
        for (CategoriaInsumo c : values()) {
            if (c.getId().equals(id)) return c;
        }
        throw new IllegalArgumentException("CategoriaInsumo id inválido: " + id);
    }
}
