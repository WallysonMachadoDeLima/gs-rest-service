package br.com.softlutions.contrutora.modules.obra.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.softlutions.contrutora.modules.obra.entities.Obra;
import br.com.softlutions.contrutora.modules.obra.services.ObraService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/obras")
@Tag(name = "Obras")
public class ObraController {
    @Autowired
    private ObraService service;

    @GetMapping
    public List<Obra> listar() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Obra> buscar(@PathVariable @NonNull Integer id) {
        return service.findOne(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
public Obra criar(@RequestBody @NonNull Obra obra) {
        return service.create(obra);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Obra> atualizar(@PathVariable @NonNull Integer id, @RequestBody @NonNull Obra dados) {
        return service.update(id, dados)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable @NonNull Integer id) {
        if (service.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
