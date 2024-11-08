package br.com.pedromonteiro.biblioteca.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TBL_LIVROS", schema = "BIBLIOTECA")
public class LivroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODIGO", unique = true, nullable = false, columnDefinition = "BIGINT COMMENT 'Código do registro'")
    private Long id;

    @Column(name = "TITULO", nullable = false, unique = true, columnDefinition = "VARCHAR(255) COMMENT 'Título do livro'")
    private String titulo;

    @Column(name = "ISBN", length = 13, nullable = false, unique = true, columnDefinition = "VARCHAR(13) COMMENT 'Código ISBN do livro'")
    private String isbn;

    @Column(name = "COD_AUTOR", nullable = false, columnDefinition = "BIGINT COMMENT 'Código do autor do livro'")
    private Long autor_id;

    @Column(name = "COD_CATEGORIA", nullable = false, columnDefinition = "BIGINT COMMENT 'Código da categoria do livro'")
    private Long categoria_id;

}