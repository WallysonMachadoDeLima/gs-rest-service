package br.com.softlutions.contrutora.modules.fornecedor.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import br.com.softlutions.contrutora.modules.fornecedor.entities.Fornecedor;
import br.com.softlutions.contrutora.modules.fornecedor.repositories.FornecedorRepository;

@Service
public class FornecedorService {
    @Autowired
    private FornecedorRepository repository;

    public List<Fornecedor> findAll() {
        return repository.findAll();
    }

    public Optional<Fornecedor> findOne(@NonNull Integer id) {
        return repository.findById(id);
    }

    public Fornecedor create(@NonNull Fornecedor fornecedor) {
        return repository.save(fornecedor);
    }

    public Optional<Fornecedor> update(@NonNull Integer id, @NonNull Fornecedor dados) {
        return repository.findById(id)
                .map(f -> {
                    f.setTipo(dados.getTipo());
                    f.setNome(dados.getNome());
                    f.setDocumento(dados.getDocumento());
                    f.setTelefone(dados.getTelefone());
                    f.setEnderecoTexto(dados.getEnderecoTexto());
                    f.setObservacao(dados.getObservacao());
                    f.setAtivo(dados.getAtivo());
                    return repository.save(f);
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
