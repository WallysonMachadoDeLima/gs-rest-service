
package br.com.seuprojeto.enums;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class EnumCatalogService {
	public List<EnumOptionDto> getOne(String key, boolean includeDeprecated) {
		EnumKey enumKey = EnumKey.fromKey(key);
		return enumKey.getOptions(includeDeprecated);
	}

	public Map<String, List<EnumOptionDto>> getAll(boolean includeDeprecated) {
		return EnumKey.getAll(includeDeprecated);
	}
}

