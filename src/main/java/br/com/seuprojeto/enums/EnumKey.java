package br.com.seuprojeto.enums;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.softlutions.contrutora.modules.colaborador.entities.Profissao;
import br.com.softlutions.contrutora.modules.obra.entities.StatusObra;
import br.com.softlutions.shared.RegistroStatus;

public enum EnumKey {
    PROFISSAO("profissao", includeDeprecated -> EnumOptions.of(Profissao.values(), includeDeprecated)),
    STATUS_OBRA("statusObra", includeDeprecated -> EnumOptions.of(StatusObra.values(), includeDeprecated)),
    REGISTRO_STATUS("registroStatus", includeDeprecated -> EnumOptions.of(RegistroStatus.values(), includeDeprecated));

    private final String key;
    private final Function<Boolean, List<EnumOptionDto>> provider;

    EnumKey(String key, Function<Boolean, List<EnumOptionDto>> provider) {
        this.key = key;
        this.provider = provider;
    }

    public String getKey() { return key; }
    public List<EnumOptionDto> getOptions(boolean includeDeprecated) {
        return provider.apply(includeDeprecated);
    }

    public static EnumKey fromKey(String key) {
        for (EnumKey k : values()) {
            if (k.key.equals(key)) return k;
        }
        throw new IllegalArgumentException("EnumKey inválido: " + key);
    }

    public static Map<String, List<EnumOptionDto>> getAll(boolean includeDeprecated) {
        Map<String, List<EnumOptionDto>> map = new LinkedHashMap<>();
        for (EnumKey k : values()) {
            map.put(k.key, k.getOptions(includeDeprecated));
        }
        return map;
    }
}
