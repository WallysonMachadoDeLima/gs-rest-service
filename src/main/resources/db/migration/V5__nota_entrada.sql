CREATE TABLE IF NOT EXISTS nota_entrada (
    id SERIAL PRIMARY KEY,
    obra_id INTEGER NOT NULL REFERENCES obra(id),
    fornecedor_id INTEGER NOT NULL REFERENCES fornecedor(id),
    numero_nota VARCHAR(50),
    data DATE NOT NULL,
    valor_total NUMERIC(12,2),
    status VARCHAR(20) NOT NULL DEFAULT 'RASCUNHO',
    observacao TEXT,
    criado_em TIMESTAMP NOT NULL DEFAULT NOW(),
    ativo BOOLEAN DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS nota_entrada_item (
    id SERIAL PRIMARY KEY,
    nota_entrada_id INTEGER NOT NULL REFERENCES nota_entrada(id) ON DELETE CASCADE,
    insumo_id INTEGER NOT NULL REFERENCES insumo(id),
    quantidade NUMERIC(12,3) NOT NULL,
    valor_unitario NUMERIC(12,2) NOT NULL,
    valor_total NUMERIC(12,2) NOT NULL,
    observacao TEXT
);

ALTER TABLE obra_insumo_lancamento
    ADD COLUMN IF NOT EXISTS nota_entrada_id INTEGER REFERENCES nota_entrada(id),
    ADD COLUMN IF NOT EXISTS origem_compra INTEGER;

CREATE INDEX IF NOT EXISTS idx_lancamento_nota_entrada_id ON obra_insumo_lancamento(nota_entrada_id);
