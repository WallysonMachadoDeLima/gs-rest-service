package url.com.br.obra;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObraService {
    @Autowired
    private ObraRepository repository;

    public List<Obra> listarTodos() {
        return repository.findAll();
    }

    public Optional<Obra> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Obra criar(Obra obra) {
        return repository.save(obra);
    }

    public Optional<Obra> atualizar(Long id, Obra dados) {
        return repository.findById(id)
                .map(o -> {
                    o.setTerrenoId(dados.getTerrenoId());
                    o.setNome(dados.getNome());
                    o.setDataInicio(dados.getDataInicio());
                    o.setDataFimPrevista(dados.getDataFimPrevista());
                    o.setStatus(dados.getStatus());
                    o.setObservacao(dados.getObservacao());
                    o.setAtivo(dados.getAtivo());
                    return repository.save(o);
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
