-- Migration para o módulo de Obras MVP (Terrenos, Insumos, Fornecedores e Lançamentos)

-- Atualizar tabela terreno conforme documentação MVP
ALTER TABLE terreno 
    ADD COLUMN IF NOT EXISTS area_m2 NUMERIC(10,2),
    ADD COLUMN IF NOT EXISTS valor_aquisicao NUMERIC(15,2),
    ADD COLUMN IF NOT EXISTS data_aquisicao DATE,
    ADD COLUMN IF NOT EXISTS observacao TEXT;

-- Atualizar tabela obra conforme documentação MVP
ALTER TABLE obra 
    DROP COLUMN IF EXISTS status,
    ADD COLUMN IF NOT EXISTS status_id INTEGER NOT NULL DEFAULT 1;

-- Tabela: unidade_medida
CREATE TABLE IF NOT EXISTS unidade_medida (
    id SERIAL PRIMARY KEY,
    sigla VARCHAR(20) NOT NULL,
    nome VARCHAR(100) NOT NULL,
    ativo BOOLEAN DEFAULT TRUE
);

-- Tabela: insumo
CREATE TABLE IF NOT EXISTS insumo (
    id SERIAL PRIMARY KEY,
    tipo_id INTEGER NOT NULL,
    nome VARCHAR(255) NOT NULL,
    categoria_id INTEGER NOT NULL,
    unidade_id INTEGER REFERENCES unidade_medida(id),
    controla_estoque BOOLEAN DEFAULT TRUE,
    custo_padrao NUMERIC(12,2),
    descricao TEXT,
    ativo BOOLEAN DEFAULT TRUE
);

-- Tabela: fornecedor
CREATE TABLE IF NOT EXISTS fornecedor (
    id SERIAL PRIMARY KEY,
    tipo_id INTEGER NOT NULL,
    nome VARCHAR(255) NOT NULL,
    documento VARCHAR(20),
    telefone VARCHAR(30) NOT NULL,
    endereco_texto TEXT,
    observacao TEXT,
    ativo BOOLEAN DEFAULT TRUE
);

-- Tabela: obra_insumo_lancamento
CREATE TABLE IF NOT EXISTS obra_insumo_lancamento (
    id SERIAL PRIMARY KEY,
    obra_id INTEGER NOT NULL REFERENCES obra(id),
    insumo_id INTEGER NOT NULL REFERENCES insumo(id),
    fornecedor_id INTEGER REFERENCES fornecedor(id),
    prestador_colaborador_id INTEGER REFERENCES colaborador(id),
    criado_por_colaborador_id INTEGER REFERENCES colaborador(id),
    tipo_lancamento_id INTEGER NOT NULL,
    data DATE NOT NULL,
    quantidade NUMERIC(12,3) NOT NULL,
    valor_unitario NUMERIC(12,2) NOT NULL,
    valor_total NUMERIC(12,2) NOT NULL,
    observacao TEXT,
    ativo BOOLEAN DEFAULT TRUE
);

-- Índices recomendados para performance
CREATE INDEX IF NOT EXISTS idx_lancamento_obra_id ON obra_insumo_lancamento(obra_id);
CREATE INDEX IF NOT EXISTS idx_lancamento_obra_data ON obra_insumo_lancamento(obra_id, data);
CREATE INDEX IF NOT EXISTS idx_lancamento_fornecedor_id ON obra_insumo_lancamento(fornecedor_id);
CREATE INDEX IF NOT EXISTS idx_lancamento_insumo_id ON obra_insumo_lancamento(insumo_id);

-- Inserir unidades de medida padrão
INSERT INTO unidade_medida (sigla, nome) VALUES
    ('un', 'Unidade'),
    ('kg', 'Quilograma'),
    ('saco', 'Saco'),
    ('m', 'Metro'),
    ('m2', 'Metro quadrado'),
    ('m3', 'Metro cúbico'),
    ('hora', 'Hora'),
    ('diaria', 'Diária'),
    ('l', 'Litro'),
    ('tubo', 'Tubo')
ON CONFLICT DO NOTHING;
