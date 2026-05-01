# Estrutura do Projeto — back-obra

## Stack

| Camada       | Tecnologia                        |
|--------------|-----------------------------------|
| Framework    | Spring Boot 3.2 (Java 21)         |
| ORM          | Spring Data JPA + Hibernate       |
| Banco        | PostgreSQL 15                     |
| Migrations   | Flyway                            |
| Validação    | Bean Validation (Jakarta)         |
| Docs         | SpringDoc OpenAPI (Swagger UI)    |

---

## Estrutura de Pacotes

```
br.com.softlutions.contrutora/
├── RestServiceApplication.java          # Entry point
│
├── infra/
│   ├── db/
│   │   └── JpaConfig.java               # Configuração JPA
│   ├── integrations/
│   │   └── OpenApiConfig.java           # Swagger / OpenAPI
│   └── web/
│       └── CorsConfig.java              # CORS
│
└── modules/
    ├── shared/
    │   ├── controllers/
    │   │   └── EnumCatalogController.java
    │   └── services/
    │       └── EnumCatalogService.java
    │
    ├── obra/
    │   ├── controllers/    ObraController.java
    │   ├── entities/       Obra.java · StatusObra.java · StatusObraIdConverter.java
    │   ├── repositories/   ObraRepository.java
    │   └── services/       ObraService.java
    │
    ├── terreno/
    │   ├── controllers/    TerrenoController.java
    │   ├── entities/       Terreno.java
    │   ├── repositories/   TerrenoRepository.java
    │   └── services/       TerrenoService.java
    │
    ├── insumo/
    │   ├── controllers/    InsumoController.java · UnidadeMedidaController.java
    │   ├── entities/       Insumo.java · CategoriaInsumo.java · TipoInsumo.java · UnidadeMedida.java
    │   ├── repositories/   InsumoRepository.java · UnidadeMedidaRepository.java
    │   └── services/       InsumoService.java · UnidadeMedidaService.java
    │
    ├── colaborador/
    │   ├── controllers/    ColaboradorController.java
    │   ├── entities/       Colaborador.java · Profissao.java · ProfissaoIdConverter.java
    │   ├── repositories/   ColaboradorRepository.java
    │   └── services/       ColaboradorService.java
    │
    ├── fornecedor/
    │   ├── controllers/    FornecedorController.java
    │   ├── entities/       Fornecedor.java · TipoFornecedor.java
    │   ├── repositories/   FornecedorRepository.java
    │   └── services/       FornecedorService.java
    │
    ├── lancamento/
    │   ├── controllers/    ObraInsumoLancamentoController.java
    │   ├── entities/       ObraInsumoLancamento.java · TipoLancamento.java · OrigemCompra.java
    │   ├── repositories/   ObraInsumoLancamentoRepository.java
    │   └── services/       ObraInsumoLancamentoService.java
    │
    └── notaentrada/
        ├── controllers/    NotaEntradaController.java · NotaEntradaRequest.java
        ├── entities/       NotaEntrada.java · NotaEntradaItem.java · NotaStatus.java
        ├── repositories/   NotaEntradaRepository.java · NotaEntradaItemRepository.java
        └── services/       NotaEntradaService.java

br.com.softlutions.shared/
    ├── RegistroStatus.java              # Enum base de status (ATIVO/INATIVO)
    └── RegistroStatusIdConverter.java   # JPA AttributeConverter para RegistroStatus
```

---

## Migrations (Flyway)

```
src/main/resources/db/migration/
├── V1__estrutura_inicial.sql
├── V2__modulo_obras_mvp.sql
├── V3__lancamento_data_para_timestamp.sql
├── V4__lancamento_criado_em.sql
└── V5__nota_entrada.sql
```

Nomenclatura: `V{N}__{descricao_snake_case}.sql`

---

## Convenção de Nomenclatura

| Elemento          | Padrão                          | Exemplo                        |
|-------------------|---------------------------------|--------------------------------|
| Pacote módulo     | `modules.[nome]`                | `modules.insumo`               |
| Entity            | `PascalCase` + sem sufixo       | `Insumo`, `NotaEntrada`        |
| Enum de domínio   | `PascalCase`                    | `TipoInsumo`, `NotaStatus`     |
| Converter JPA     | `[Enum]IdConverter`             | `ProfissaoIdConverter`         |
| Repository        | `[Entity]Repository`            | `InsumoRepository`             |
| Service           | `[Entity]Service`               | `InsumoService`                |
| Controller        | `[Entity]Controller`            | `InsumoController`             |
| DTO request       | `[Entity]Request` (record)      | `NotaEntradaRequest`           |
| DTO response      | `[Entity]Response` (record)     | — (inline ou projeção JPA)     |
| Migration         | `V{N}__descricao.sql`           | `V6__nova_feature.sql`         |

---

## Padrão de Módulo

Cada módulo é auto-contido:

```
modules/[nome]/
├── controllers/   @RestController + @RequestMapping
├── entities/      @Entity, enums de domínio, @Converter
├── repositories/  @Repository extends JpaRepository
└── services/      @Service com lógica de negócio
```

Controllers delegam 100% para services. Services usam apenas o próprio repository (ou injetam services de outros módulos quando necessário).
