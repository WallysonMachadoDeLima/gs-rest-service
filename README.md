
# Sistema de Gestão de Obras

Sistema RESTful para gestão de obras, terrenos e colaboradores desenvolvido com Spring Boot, totalmente containerizado.

## 🚀 Tecnologias

- **Java 21**
- **Spring Boot 3.2.1**
- **PostgreSQL**
- **Flyway** (migrations)
- **Docker** (ambiente obrigatório)
- **Swagger/OpenAPI** (documentação)

## 📋 Pré-requisitos

- Docker e Docker Compose

## 🏃‍♂️ Como Executar (Somente Docker)

```bash
# Subir todo o ambiente (banco e aplicação)
docker-compose up -d

# Ver logs
docker-compose logs -f

# Parar serviços
docker-compose down

# Reconstruir containers
docker-compose up --build
```

## 🛠️ Comandos Úteis (Makefile)

Você pode usar o Makefile para facilitar o desenvolvimento:

```bash
# Subir ambiente
make up

# Parar ambiente
make down

# Reconstruir containers
make build

# Ver logs
make logs

# Rodar testes
make test

# Flyway: status das migrations
make flyway-info

# Flyway: executar migrations
make flyway-migrate

# Flyway: limpar banco (cuidado!)
make flyway-clean
```

## 📚 Documentação da API

- **Swagger UI**: http://localhost:8080/swagger-ui/index.html
- **OpenAPI JSON**: http://localhost:8080/v3/api-docs

## 🗄️ Migrations

Scripts de migration estão em: `src/main/resources/db/migration/`

Para criar nova migration:
```bash
touch src/main/resources/db/migration/V2__nova_migration.sql
```

## 🔧 Configurações

As variáveis de ambiente podem ser configuradas no arquivo `.env` (baseado em `.env.example`).

## 📖 Documentação Detalhada

- [API Endpoints](http://localhost:8080/swagger-ui/index.html)