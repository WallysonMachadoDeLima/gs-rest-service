package br.com.softlutions.shared;

import br.com.seuprojeto.enums.EnumIdAttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RegistroStatusIdConverter extends EnumIdAttributeConverter<RegistroStatus> {
    public RegistroStatusIdConverter() {
        super(RegistroStatus.class);
    }
}
