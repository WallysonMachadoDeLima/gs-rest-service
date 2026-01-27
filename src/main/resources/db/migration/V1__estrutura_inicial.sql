-- Migration para criar as tabelas principais do sistema

CREATE TABLE IF NOT EXISTS terreno (
    id SERIAL PRIMARY KEY,
    nome_apelido VARCHAR(255) NOT NULL,
    endereco_texto VARCHAR(255),
    largura NUMERIC(10,2),
    altura NUMERIC(10,2),
    matricula VARCHAR(100),
    ativo BOOLEAN DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS colaborador (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    cpf VARCHAR(20),
    telefone VARCHAR(30),
    endereco VARCHAR(255),
    profissao_id INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS obra (
    id SERIAL PRIMARY KEY,
    terreno_id INTEGER,
    nome VARCHAR(255) NOT NULL,
    data_inicio DATE,
    data_fim_prevista DATE,
    status_id INTEGER NOT NULL,
    observacao VARCHAR(255),
    ativo BOOLEAN DEFAULT TRUE
);

-- Outras tabelas podem ser adicionadas conforme necessário
