package br.com.softlutions.contrutora.modules.shared.controllers;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.seuprojeto.enums.EnumOptionDto;
import br.com.softlutions.contrutora.modules.shared.services.EnumCatalogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/enums")
@Tag(name = "Catálogo de Enums", description = "Rotas para consulta de enums expostos na API.")
public class EnumCatalogController {
    private final EnumCatalogService service;

    public EnumCatalogController(EnumCatalogService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Lista todos os enums disponíveis", description = "Retorna um mapa de enums disponíveis para catálogo.",
        responses = @ApiResponse(responseCode = "200", description = "Mapa de enums",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = EnumOptionDto.class)))
    )
    public ResponseEntity<Map<String, List<EnumOptionDto>>> getAll(
            @Parameter(description = "Incluir enums deprecated", example = "false")
            @RequestParam(defaultValue = "false") boolean includeDeprecated) {
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(6, TimeUnit.HOURS))
                .body(service.getAll(includeDeprecated));
    }

    @GetMapping("/{key}")
    @Operation(summary = "Lista opções de um enum específico", description = "Retorna as opções do enum informado.",
        responses = @ApiResponse(responseCode = "200", description = "Lista de opções do enum",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = EnumOptionDto.class)))
    )
    public ResponseEntity<List<EnumOptionDto>> getOne(
            @Parameter(description = "Chave do enum (ex: statusObra)") @PathVariable String key,
            @Parameter(description = "Incluir enums deprecated", example = "false")
            @RequestParam(defaultValue = "false") boolean includeDeprecated) {
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(6, TimeUnit.HOURS))
                .body(service.getOne(key, includeDeprecated));
    }
}
