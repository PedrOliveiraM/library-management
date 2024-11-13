package br.com.pedromonteiro.biblioteca.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "Livro")
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "autor_id")
    @JsonBackReference
    private AutorEntity autor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoria_id")
    @JsonBackReference
    private CategoriaEntity categoria;

}
