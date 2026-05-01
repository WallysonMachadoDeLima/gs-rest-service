# Como criar um novo módulo — back-obra

## 1. Crie a pasta do módulo

```
src/main/java/br/com/softlutions/contrutora/modules/exemplo/
├── controllers/
├── entities/
├── repositories/
└── services/
```

## 2. Crie os arquivos base

**Entity**
```java
package br.com.softlutions.contrutora.modules.exemplo.entities;

import br.com.softlutions.shared.RegistroStatus;
import br.com.softlutions.shared.RegistroStatusIdConverter;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "exemplo")
public class Exemplo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Convert(converter = RegistroStatusIdConverter.class)
    @Column(nullable = false)
    private RegistroStatus status = RegistroStatus.ATIVO;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    // getters e setters
}
```

**Repository**
```java
package br.com.softlutions.contrutora.modules.exemplo.repositories;

import br.com.softlutions.contrutora.modules.exemplo.entities.Exemplo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExemploRepository extends JpaRepository<Exemplo, Integer> {
}
```

**DTOs (records)**
```java
// controllers/ExemploRequest.java
package br.com.softlutions.contrutora.modules.exemplo.controllers;

import jakarta.validation.constraints.NotBlank;

public record ExemploRequest(
    @NotBlank String nome
) {}

// controllers/ExemploResponse.java
package br.com.softlutions.contrutora.modules.exemplo.controllers;

import java.time.LocalDateTime;

public record ExemploResponse(Integer id, String nome, String status, LocalDateTime createdAt) {}
```

**Service**
```java
package br.com.softlutions.contrutora.modules.exemplo.services;

import br.com.softlutions.contrutora.modules.exemplo.controllers.ExemploRequest;
import br.com.softlutions.contrutora.modules.exemplo.controllers.ExemploResponse;
import br.com.softlutions.contrutora.modules.exemplo.entities.Exemplo;
import br.com.softlutions.contrutora.modules.exemplo.repositories.ExemploRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExemploService {

    private final ExemploRepository repository;

    public ExemploService(ExemploRepository repository) {
        this.repository = repository;
    }

    public ExemploResponse criar(ExemploRequest req) {
        Exemplo entity = new Exemplo();
        entity.setNome(req.nome());
        repository.save(entity);
        return toResponse(entity);
    }

    public List<ExemploResponse> listar() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    public ExemploResponse buscarPorId(Integer id) {
        return repository.findById(id)
            .map(this::toResponse)
            .orElseThrow(() -> new RuntimeException("Exemplo não encontrado: " + id));
    }

    public ExemploResponse atualizar(Integer id, ExemploRequest req) {
        Exemplo entity = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Exemplo não encontrado: " + id));
        entity.setNome(req.nome());
        repository.save(entity);
        return toResponse(entity);
    }

    public void remover(Integer id) {
        repository.deleteById(id);
    }

    private ExemploResponse toResponse(Exemplo e) {
        return new ExemploResponse(e.getId(), e.getNome(), e.getStatus().name(), e.getCreatedAt());
    }
}
```

**Controller**
```java
package br.com.softlutions.contrutora.modules.exemplo.controllers;

import br.com.softlutions.contrutora.modules.exemplo.services.ExemploService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Exemplo")
@RestController
@RequestMapping("/exemplos")
public class ExemploController {

    private final ExemploService service;

    public ExemploController(ExemploService service) {
        this.service = service;
    }

    @Operation(summary = "Criar")
    @PostMapping
    public ExemploResponse criar(@RequestBody @Valid ExemploRequest request) {
        return service.criar(request);
    }

    @Operation(summary = "Listar todos")
    @GetMapping
    public List<ExemploResponse> listar() {
        return service.listar();
    }

    @Operation(summary = "Buscar por ID")
    @GetMapping("/{id}")
    public ExemploResponse buscarPorId(@PathVariable Integer id) {
        return service.buscarPorId(id);
    }

    @Operation(summary = "Atualizar")
    @PutMapping("/{id}")
    public ExemploResponse atualizar(@PathVariable Integer id, @RequestBody @Valid ExemploRequest request) {
        return service.atualizar(id, request);
    }

    @Operation(summary = "Remover")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Integer id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }
}
```

## 3. Crie a migration

Próximo número disponível após o último `V{N}` em `src/main/resources/db/migration/`:

```sql
-- V6__exemplo.sql
CREATE TABLE exemplo (
    id         SERIAL PRIMARY KEY,
    nome       VARCHAR(255) NOT NULL,
    status     SMALLINT     NOT NULL DEFAULT 1,
    created_at TIMESTAMP             DEFAULT NOW()
);
```

> `status SMALLINT DEFAULT 1` segue o padrão de `RegistroStatus` (1 = ATIVO, 0 = INATIVO).

## 4. Enum de domínio (opcional)

Se o módulo tiver um enum de domínio próprio (ex: `TipoInsumo`):

```java
package br.com.softlutions.contrutora.modules.exemplo.entities;

public enum TipoExemplo {
    TIPO_A(1), TIPO_B(2);

    private final int id;
    TipoExemplo(int id) { this.id = id; }
    public int getId() { return id; }
    public static TipoExemplo fromId(int id) {
        for (TipoExemplo t : values()) if (t.id == id) return t;
        throw new IllegalArgumentException("TipoExemplo desconhecido: " + id);
    }
}
```

E o converter JPA:

```java
package br.com.softlutions.contrutora.modules.exemplo.entities;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class TipoExemploIdConverter implements AttributeConverter<TipoExemplo, Integer> {
    public Integer convertToDatabaseColumn(TipoExemplo attr) { return attr == null ? null : attr.getId(); }
    public TipoExemplo convertToEntityAttribute(Integer id)  { return id  == null ? null : TipoExemplo.fromId(id); }
}
```

## 5. Relacionamento com outro módulo

Injete o repository (ou service) do módulo relacionado diretamente no service:

```java
@Service
public class ExemploService {
    private final ExemploRepository repository;
    private final ObraRepository obraRepository;   // módulo externo

    public ExemploService(ExemploRepository repository, ObraRepository obraRepository) {
        this.repository    = repository;
        this.obraRepository = obraRepository;
    }
}
```

## Checklist

- [ ] Pasta `modules/[nome]/controllers · entities · repositories · services`
- [ ] Entity com `@Entity`, `@Table`, `@Id @GeneratedValue`
- [ ] Repository `extends JpaRepository<Entidade, Integer>`
- [ ] Records `[Nome]Request` (com validação) e `[Nome]Response`
- [ ] Service com método `toResponse` privado
- [ ] Controller com `@Tag`, `@RestController`, `@RequestMapping`
- [ ] Migration `V{N}__[nome].sql` com tabela e coluna `status SMALLINT DEFAULT 1`
- [ ] Enum de domínio + Converter (se necessário)
