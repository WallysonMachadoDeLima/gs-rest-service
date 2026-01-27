package br.com.seuprojeto.enums;

import jakarta.persistence.AttributeConverter;

public abstract class EnumIdAttributeConverter<E extends Enum<E> & DbEnum<Integer>> implements AttributeConverter<E, Integer> {
    private final Class<E> enumClass;

    protected EnumIdAttributeConverter(Class<E> enumClass) {
        this.enumClass = enumClass;
    }

    @Override
    public Integer convertToDatabaseColumn(E attribute) {
        return attribute != null ? attribute.getId() : null;
    }

    @Override
    public E convertToEntityAttribute(Integer dbData) {
        if (dbData == null) return null;
        for (E e : enumClass.getEnumConstants()) {
            if (e.getId().equals(dbData)) {
                return e;
            }
        }
        throw new IllegalArgumentException("Invalid id for enum " + enumClass.getSimpleName() + ": " + dbData);
    }
}
