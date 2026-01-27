package br.com.softlutions.contrutora.modules.obra.entities;

import br.com.seuprojeto.enums.EnumIdAttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusObraIdConverter extends EnumIdAttributeConverter<StatusObra> {
    public StatusObraIdConverter() {
        super(StatusObra.class);
    }
}
