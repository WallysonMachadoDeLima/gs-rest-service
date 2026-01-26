# Sistema de Gestão de Obras

Sistema RESTful para gestão de obras, terrenos e colaboradores desenvolvido com Spring Boot.

## 🚀 Tecnologias

- **Java 21**
- **Spring Boot 3.2.1**
- **PostgreSQL** (produção)
- **H2** (desenvolvimento)
- **Flyway** (migrations)
- **Docker** (containerização)
- **Swagger/OpenAPI** (documentação)

## 📋 Pré-requisitos

- Java 21 ou superior
- Maven 3.6+
- Docker e Docker Compose (opcional, para desenvolvimento)

## 🏃‍♂️ Como Executar

### Opção 1: Com Docker (Recomendado)

```bash
# Executar script de inicialização
./start-postgres.sh

# Ou manualmente
docker-compose up -d
```

### Opção 2: Desenvolvimento Local

```bash
# Usar H2 (banco em memória)
./mvnw spring-boot:run

# Ou conectar em PostgreSQL local
# 1. Instalar PostgreSQL
# 2. Criar banco 'obras_db'
# 3. Executar: ./mvnw spring-boot:run
```

## 📚 Documentação da API

- **Swagger UI**: http://localhost:8080/swagger-ui/index.html
- **OpenAPI JSON**: http://localhost:8080/v3/api-docs

## 🗄️ Banco de Dados

### Estrutura das Tabelas

- **obra**: Cadastro de obras
- **terreno**: Cadastro de terrenos
- **colaborador**: Cadastro de colaboradores

### Migrations

Localizadas em: `src/main/resources/db/migration/`

Para criar nova migration:
```bash
touch src/main/resources/db/migration/V2__nova_migration.sql
```

## 🔧 Configurações

### Perfis Disponíveis

- **default**: PostgreSQL (produção)
- **dev**: H2 em memória (desenvolvimento)

```bash
# Usar perfil de desenvolvimento
./mvnw spring-boot:run -Dspring.profiles.active=dev
```

## 🐳 Comandos Docker

```bash
# Iniciar serviços
docker-compose up -d

# Ver logs
docker-compose logs -f

# Parar serviços
docker-compose down

# Reconstruir
docker-compose up --build
```

## 🧪 Testes

```bash
# Executar testes
./mvnw test

# Com cobertura
./mvnw test jacoco:report
```

## 📁 Estrutura do Projeto

```
src/
├── main/
│   ├── java/br/com/softlutions/contrutora/
│   │   ├── modules/
│   │   │   ├── obra/
│   │   │   ├── terreno/
│   │   │   └── colaborador/
│   │   ├── shared/
│   │   └── RestServiceApplication.java
│   └── resources/
│       ├── db/migration/
│       ├── application.properties
│       └── application-dev.properties
└── test/
```

## 🔄 Migrations Flyway

### Comandos Úteis

```bash
# Verificar status
./mvnw flyway:info

# Executar migrations
./mvnw flyway:migrate

# Limpar banco (cuidado!)
./mvnw flyway:clean
```

## 📖 Documentação Detalhada

- [Configuração PostgreSQL](POSTGRESQL_README.md)
- [API Endpoints](http://localhost:8080/swagger-ui/index.html)

## 🤝 Contribuição

1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/nova-feature`)
3. Commit suas mudanças (`git commit -am 'Adiciona nova feature'`)
4. Push para a branch (`git push origin feature/nova-feature`)
5. Abra um Pull Request

## 📝 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE.txt) para mais detalhes.