-- Criação do esquema
CREATE SCHEMA IF NOT EXISTS BIBLIOTECA;

-- Tabela de Autores
CREATE TABLE IF NOT EXISTS BIBLIOTECA.TBL_AUTORES (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT 'Código do registro do autor',
    AUTOR VARCHAR(255) NOT NULL COMMENT 'Nome do autor',
    NACIONALIDADE VARCHAR(255) NOT NULL COMMENT 'Nacionalidade do autor'
);

-- Inserindo registros em TBL_AUTORES
INSERT INTO BIBLIOTECA.TBL_AUTORES (AUTOR, NACIONALIDADE) VALUES
('Jorge Amado', 'Brasileiro'),
('Gabriel García Márquez', 'Colombiano'),
('Jane Austen', 'Britânico');

-- Tabela de Categorias
CREATE TABLE IF NOT EXISTS BIBLIOTECA.TBL_CATEGORIAS (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT 'Código do registro da categoria',
    AUTOR VARCHAR(255) NOT NULL COMMENT 'Nome da categoria'
);

-- Inserindo registros em TBL_CATEGORIAS
INSERT INTO biblioteca.tbl_categorias (nome) VALUES
('Romance'),
('Ficção Científica'),
('Biografia');

-- Tabela de Livros
CREATE TABLE IF NOT EXISTS BIBLIOTECA.TBL_LIVROS (
    CODIGO BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT 'Código do registro',
    TITULO VARCHAR(255) NOT NULL UNIQUE COMMENT 'Título do livro',
    ISBN VARCHAR(13) NOT NULL UNIQUE COMMENT 'Código ISBN do livro',
    AUTOR_ID BIGINT NOT NULL,
    CATEGORIA_ID BIGINT NOT NULL,
    FOREIGN KEY (AUTOR_ID) REFERENCES BIBLIOTECA.TBL_AUTORES(ID),
    FOREIGN KEY (CATEGORIA_ID) REFERENCES BIBLIOTECA.TBL_CATEGORIAS(ID)
);

-- Inserindo registros em TBL_LIVROS
INSERT INTO BIBLIOTECA.TBL_LIVROS (TITULO, ISBN, AUTOR_ID, CATEGORIA_ID) VALUES
('Capitães da Areia', '9788573602857', 1, 1),
('Cem Anos de Solidão', '9780307474728', 2, 1),
('Orgulho e Preconceito', '9780141199078', 3, 1);
