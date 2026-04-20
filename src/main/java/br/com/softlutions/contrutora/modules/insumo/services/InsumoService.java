package br.com.softlutions.contrutora.modules.insumo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import br.com.softlutions.contrutora.modules.insumo.entities.Insumo;
import br.com.softlutions.contrutora.modules.insumo.repositories.InsumoRepository;

@Service
public class InsumoService {
    @Autowired
    private InsumoRepository repository;

    public List<Insumo> findAll() {
        return repository.findAll();
    }

    public Optional<Insumo> findOne(@NonNull Integer id) {
        return repository.findById(id);
    }

    public Insumo create(@NonNull Insumo insumo) {
        return repository.save(insumo);
    }

    public Optional<Insumo> update(@NonNull Integer id, @NonNull Insumo dados) {
        return repository.findById(id)
                .map(i -> {
                    i.setTipo(dados.getTipo());
                    i.setNome(dados.getNome());
                    i.setCategoria(dados.getCategoria());
                    i.setUnidadeId(dados.getUnidadeId());
                    i.setControlaEstoque(dados.getControlaEstoque());
                    i.setCustoPadrao(dados.getCustoPadrao());
                    i.setDescricao(dados.getDescricao());
                    i.setAtivo(dados.getAtivo());
                    return repository.save(i);
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
