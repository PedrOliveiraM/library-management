package br.com.pedromonteiro.biblioteca.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference
    private AutorEntity autor;

    // teste 2
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoria_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private CategoriaEntity categoria;
}
