package br.com.softlutions.contrutora.modules.notaentrada.controllers;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.softlutions.contrutora.modules.notaentrada.entities.NotaEntrada;
import br.com.softlutions.contrutora.modules.notaentrada.entities.NotaEntradaItem;
import br.com.softlutions.contrutora.modules.notaentrada.services.NotaEntradaService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/notas-entrada")
@Tag(name = "Notas de Entrada")
public class NotaEntradaController {
    @Autowired
    private NotaEntradaService service;

    @GetMapping
    public List<NotaEntrada> listar(@RequestParam(required = false) Integer obraId) {
        if (obraId != null) {
            return service.findAll().stream()
                    .filter(n -> obraId.equals(n.getObraId()))
                    .toList();
        }
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotaEntrada> buscar(@PathVariable @NonNull Integer id) {
        return service.findOne(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/itens")
    public ResponseEntity<List<NotaEntradaItem>> buscarItens(@PathVariable @NonNull Integer id) {
        if (service.findOne(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.findItens(id));
    }

    @PostMapping
    public NotaEntrada criar(@RequestBody @NonNull NotaEntradaRequest request) {
        return service.create(request.getNota(), request.getItens());
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotaEntrada> atualizar(@PathVariable @NonNull Integer id, @RequestBody @NonNull NotaEntradaRequest request) {
        return service.update(id, request.getNota(), request.getItens())
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

    @PostMapping("/{id}/confirmar")
    public ResponseEntity<NotaEntrada> confirmar(@PathVariable @NonNull Integer id) {
        try {
            NotaEntrada nota = service.confirmar(id);
            return ResponseEntity.ok(nota);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
