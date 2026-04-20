package br.com.softlutions.contrutora.modules.lancamento.controllers;

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

import br.com.softlutions.contrutora.modules.lancamento.entities.ObraInsumoLancamento;
import br.com.softlutions.contrutora.modules.lancamento.services.ObraInsumoLancamentoService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/lancamentos")
@Tag(name = "Lançamentos de Gastos")
public class ObraInsumoLancamentoController {
    @Autowired
    private ObraInsumoLancamentoService service;

    @GetMapping
    public List<ObraInsumoLancamento> listar(@RequestParam(required = false) Integer obraId) {
        if (obraId != null) {
            return service.findByObra(obraId);
        }
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ObraInsumoLancamento> buscar(@PathVariable @NonNull Integer id) {
        return service.findOne(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ObraInsumoLancamento criar(@RequestBody @NonNull ObraInsumoLancamento lancamento) {
        return service.create(lancamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ObraInsumoLancamento> atualizar(@PathVariable @NonNull Integer id, @RequestBody @NonNull ObraInsumoLancamento dados) {
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
