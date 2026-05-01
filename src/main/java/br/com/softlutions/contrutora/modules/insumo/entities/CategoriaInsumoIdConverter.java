package br.com.softlutions.contrutora.modules.insumo.entities;

import br.com.seuprojeto.enums.EnumIdAttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CategoriaInsumoIdConverter extends EnumIdAttributeConverter<CategoriaInsumo> {
    public CategoriaInsumoIdConverter() {
        super(CategoriaInsumo.class);
    }
}
