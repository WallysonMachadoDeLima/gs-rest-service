package br.com.seuprojeto.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EnumOptions {
    public static <E extends Enum<E> & DbEnum<Integer>> List<EnumOptionDto> of(E[] values) {
        return of(values, false);
    }
    public static <E extends Enum<E> & DbEnum<Integer>> List<EnumOptionDto> of(E[] values, boolean includeDeprecated) {
        return Arrays.stream(values)
                .filter(e -> includeDeprecated || !e.isDeprecated())
                .map(e -> new EnumOptionDto(e.getId(), e.getNome()))
                .collect(Collectors.toList());
    }
}
