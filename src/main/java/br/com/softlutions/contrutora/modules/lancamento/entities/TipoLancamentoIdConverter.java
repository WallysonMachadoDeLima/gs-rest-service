package br.com.softlutions.contrutora.modules.lancamento.entities;

import br.com.seuprojeto.enums.EnumIdAttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoLancamentoIdConverter extends EnumIdAttributeConverter<TipoLancamento> {
    public TipoLancamentoIdConverter() {
        super(TipoLancamento.class);
    }
}
