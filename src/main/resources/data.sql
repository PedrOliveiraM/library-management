INSERT INTO tbl_autores (AUTOR, NACIONALIDADE) VALUES ('Baianinho', 'Brasileiro'),
 ('João Cabral', 'Brasileiro'),
 ('Carlos Drummond', 'Brasileiro'),
 ('Clarice Lispector', 'Brasileira'),
 ('Jorge Amado', 'Brasileiro');


INSERT INTO tbl_categorias (NOME)
VALUES 
('Suspense'),
('Comedia'),
('Romance'),
('Drama');


INSERT INTO tbl_livros (TITULO,ISBN,AUTOR_ID,CATEGORIA_ID)
VALUES
('O Alquimista', '9780061122415', 1, 2),
('1984', '9780451524935', 2, 1),
('A Revolução dos Bichos', '9780451526342', 3, 1),
('O Pequeno Príncipe', '9788572322721', 4, 3);
