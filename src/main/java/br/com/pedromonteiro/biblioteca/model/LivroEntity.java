package br.com.pedromonteiro.biblioteca.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

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
@Table(name = "TBL_LIVROS", schema = "BIBLIOTECA")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity(name = "Livro")
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
    @JsonIgnoreProperties("livros") // Ignora 'livros' na serialização de 'AutorEntity' para evitar loops
    private AutorEntity autor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoria_id")
    @JsonIgnoreProperties("livros") // Ignora 'livros' na serialização de 'CategoriaEntity' para evitar loops
    private CategoriaEntity categoria;
}
