package br.com.softlutions.contrutora.modules.terreno.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.softlutions.contrutora.modules.terreno.entities.Terreno;
import br.com.softlutions.contrutora.modules.terreno.services.TerrenoService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/terrenos")
@Tag(name = "Terrenos")
public class TerrenoController {
    @Autowired
    private TerrenoService service;

    @GetMapping
    public List<Terreno> listar() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Terreno> buscar(@PathVariable Integer id) {
        return service.findOne(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Terreno criar(@RequestBody Terreno terreno) {
        return service.create(terreno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Terreno> atualizar(@PathVariable Integer id, @RequestBody Terreno dados) {
        return service.update(id, dados)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        if (service.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
