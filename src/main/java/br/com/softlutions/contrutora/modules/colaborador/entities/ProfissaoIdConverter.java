package br.com.softlutions.contrutora.modules.colaborador.entities;

import br.com.seuprojeto.enums.EnumIdAttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ProfissaoIdConverter extends EnumIdAttributeConverter<Profissao> {
    public ProfissaoIdConverter() {
        super(Profissao.class);
    }
}
