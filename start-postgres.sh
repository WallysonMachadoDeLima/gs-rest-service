#!/bin/bash

# Script para facilitar o desenvolvimento com PostgreSQL

set -e

PROJECT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd "$PROJECT_DIR"

echo "🚀 Configuração PostgreSQL para Projeto Obras"
echo "=============================================="

# Verificar se Docker está instalado
if ! command -v docker &> /dev/null; then
    echo "❌ Docker não está instalado. Instale o Docker primeiro."
    exit 1
fi

# Verificar se Docker Compose está disponível
if ! command -v docker-compose &> /dev/null && ! docker compose version &> /dev/null; then
    echo "❌ Docker Compose não está disponível."
    exit 1
fi

echo "📦 Iniciando PostgreSQL e aplicação..."

# Parar serviços existentes
docker-compose down 2>/dev/null || true

# Iniciar serviços
docker-compose up -d

echo "⏳ Aguardando PostgreSQL ficar pronto..."
sleep 10

# Verificar se PostgreSQL está saudável
if docker-compose ps postgres | grep -q "healthy\|running"; then
    echo "✅ PostgreSQL está rodando!"
    echo ""
    echo "🌐 URLs de acesso:"
    echo "  - API: http://localhost:8080"
    echo "  - Swagger UI: http://localhost:8080/swagger-ui/index.html"
    echo "  - PostgreSQL: localhost:5432 (user: postgres, pass: postgres)"
    echo ""
    echo "📊 Para conectar no banco:"
    echo "  docker exec -it obras_postgres psql -U postgres -d obras_db"
    echo ""
    echo "🛑 Para parar: docker-compose down"
else
    echo "❌ Erro ao iniciar PostgreSQL. Verifique os logs:"
    echo "  docker-compose logs postgres"
    exit 1
fi