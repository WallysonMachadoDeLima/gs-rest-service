package br.com.softlutions.contrutora.modules.insumo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import br.com.softlutions.contrutora.modules.insumo.entities.UnidadeMedida;
import br.com.softlutions.contrutora.modules.insumo.repositories.UnidadeMedidaRepository;

@Service
public class UnidadeMedidaService {
    @Autowired
    private UnidadeMedidaRepository repository;

    public List<UnidadeMedida> findAll() {
        return repository.findAll();
    }

    public Optional<UnidadeMedida> findOne(@NonNull Integer id) {
        return repository.findById(id);
    }

    public UnidadeMedida create(@NonNull UnidadeMedida unidade) {
        return repository.save(unidade);
    }

    public Optional<UnidadeMedida> update(@NonNull Integer id, @NonNull UnidadeMedida dados) {
        return repository.findById(id)
                .map(u -> {
                    u.setSigla(dados.getSigla());
                    u.setNome(dados.getNome());
                    u.setAtivo(dados.getAtivo());
                    return repository.save(u);
                });
    }

    public boolean delete(@NonNull Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
