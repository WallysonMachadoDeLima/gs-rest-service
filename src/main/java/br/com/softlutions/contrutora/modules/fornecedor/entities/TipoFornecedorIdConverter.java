package br.com.softlutions.contrutora.modules.fornecedor.entities;

import br.com.seuprojeto.enums.EnumIdAttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoFornecedorIdConverter extends EnumIdAttributeConverter<TipoFornecedor> {
    public TipoFornecedorIdConverter() {
        super(TipoFornecedor.class);
    }
}
