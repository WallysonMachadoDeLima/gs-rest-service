package url.com.br.colaborador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/colaboradores")
@CrossOrigin(origins = {"http://localhost:8085", "http://localhost:3000"})
public class ColaboradorController {
    @Autowired
    private ColaboradorRepository repository;

    @GetMapping
    public List<Colaborador> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Colaborador> buscar(@PathVariable Long id) {
        Optional<Colaborador> colaborador = repository.findById(id);
        return colaborador.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Colaborador criar(@RequestBody Colaborador colaborador) {
        return repository.save(colaborador);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Colaborador> atualizar(@PathVariable Long id, @RequestBody Colaborador dados) {
        return repository.findById(id)
                .map(c -> {
                    c.setNome(dados.getNome());
                    c.setEmail(dados.getEmail());
                    c.setTelefone(dados.getTelefone());
                    c.setEndereco(dados.getEndereco());
                    c.setProfissao(dados.getProfissao());
                    repository.save(c);
                    return ResponseEntity.ok(c);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
