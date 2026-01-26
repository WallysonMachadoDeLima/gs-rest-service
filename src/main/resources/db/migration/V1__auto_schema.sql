-- V1__auto_schema.sql
-- Migration generated from JPA entities

-- Create colaborador table
CREATE TABLE colaborador (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255),
    email VARCHAR(255),
    telefone VARCHAR(255),
    endereco TEXT,
    profissao VARCHAR(50)
);

-- Create terreno table
CREATE TABLE terreno (
    id SERIAL PRIMARY KEY,
    nome_apelido VARCHAR(255),
    endereco_texto TEXT,
    largura DECIMAL(10,2),
    altura DECIMAL(10,2),
    matricula VARCHAR(255),
    ativo BOOLEAN DEFAULT TRUE
);

-- Create obra table
CREATE TABLE obra (
    id SERIAL PRIMARY KEY,
    terreno_id INTEGER,
    nome VARCHAR(255),
    data_inicio DATE,
    data_fim_prevista DATE,
    status VARCHAR(50),
    observacao TEXT,
    ativo BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (terreno_id) REFERENCES terreno(id)
);