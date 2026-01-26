package br.com.softlutions.contrutora.modules.colaborador.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import br.com.softlutions.contrutora.modules.colaborador.entities.Colaborador;
import br.com.softlutions.contrutora.modules.colaborador.repositories.ColaboradorRepository;

@Service
public class ColaboradorService {
    @Autowired
    private ColaboradorRepository repository;

    public List<Colaborador> findAll() {
        return repository.findAll();
    }

    public Optional<Colaborador> findOne(@NonNull Integer id) {
        return repository.findById(id);
    }

    public Colaborador create(@NonNull Colaborador colaborador) {
        return repository.save(colaborador);
    }

    public Optional<Colaborador> update(@NonNull Integer id, @NonNull Colaborador dados) {
        return repository.findById(id)
                .map(c -> {
                    c.setNome(dados.getNome());
                    c.setEmail(dados.getEmail());
                    c.setCpf(dados.getCpf());
                    c.setTelefone(dados.getTelefone());
                    c.setEndereco(dados.getEndereco());
                    c.setProfissao(dados.getProfissao());
                    return repository.save(c);
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
