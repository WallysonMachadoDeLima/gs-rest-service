package url.com.br.colaborador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ColaboradorService {
    @Autowired
    private ColaboradorRepository repository;

    public List<Colaborador> listarTodos() {
        return repository.findAll();
    }

    public Optional<Colaborador> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Colaborador criar(Colaborador colaborador) {
        return repository.save(colaborador);
    }

    public Optional<Colaborador> atualizar(Long id, Colaborador dados) {
        return repository.findById(id)
                .map(c -> {
                    c.setNome(dados.getNome());
                    c.setEmail(dados.getEmail());
                    c.setTelefone(dados.getTelefone());
                    c.setEndereco(dados.getEndereco());
                    c.setProfissao(dados.getProfissao());
                    return repository.save(c);
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
