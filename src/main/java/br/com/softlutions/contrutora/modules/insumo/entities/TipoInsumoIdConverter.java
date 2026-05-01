package br.com.softlutions.contrutora.modules.insumo.entities;

import br.com.seuprojeto.enums.EnumIdAttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoInsumoIdConverter extends EnumIdAttributeConverter<TipoInsumo> {
    public TipoInsumoIdConverter() {
        super(TipoInsumo.class);
    }
}
