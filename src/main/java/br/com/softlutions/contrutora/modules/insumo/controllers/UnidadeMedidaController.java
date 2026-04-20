package br.com.softlutions.contrutora.modules.insumo.controllers;

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

import br.com.softlutions.contrutora.modules.insumo.entities.UnidadeMedida;
import br.com.softlutions.contrutora.modules.insumo.services.UnidadeMedidaService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/unidades-medida")
@Tag(name = "Unidades de Medida")
public class UnidadeMedidaController {
    @Autowired
    private UnidadeMedidaService service;

    @GetMapping
    public List<UnidadeMedida> listar() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnidadeMedida> buscar(@PathVariable @NonNull Integer id) {
        return service.findOne(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public UnidadeMedida criar(@RequestBody @NonNull UnidadeMedida unidade) {
        return service.create(unidade);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UnidadeMedida> atualizar(@PathVariable @NonNull Integer id, @RequestBody @NonNull UnidadeMedida dados) {
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
