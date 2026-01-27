package br.com.seuprojeto.enums;

public class EnumOptionDto {
    private final Integer id;
    private final String nome;

    public EnumOptionDto(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() { return id; }
    public String getNome() { return nome; }
}
