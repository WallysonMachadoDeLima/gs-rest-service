package br.com.seuprojeto.enums;

public interface DbEnum<ID> {
    ID getId();
    String getNome();
    default boolean isDeprecated() { return false; }
}
