package br.com.softlutions.contrutora.modules.terreno.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softlutions.contrutora.modules.terreno.entities.Terreno;
import br.com.softlutions.contrutora.modules.terreno.repositories.TerrenoRepository;

@Service
public class TerrenoService {
    @Autowired
    private TerrenoRepository repository;

    public List<Terreno> listarTodos() {
        return repository.findAll();
    }

    public Optional<Terreno> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Terreno criar(Terreno terreno) {
        return repository.save(terreno);
    }

    public Optional<Terreno> atualizar(Long id, Terreno dados) {
        return repository.findById(id)
                .map(t -> {
                    t.setNomeApelido(dados.getNomeApelido());
                    t.setEnderecoTexto(dados.getEnderecoTexto());
                    t.setLargura(dados.getLargura());
                    t.setAltura(dados.getAltura());
                    t.setMatricula(dados.getMatricula());
                    t.setAtivo(dados.getAtivo());
                    return repository.save(t);
                });
    }

    public boolean deletar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
