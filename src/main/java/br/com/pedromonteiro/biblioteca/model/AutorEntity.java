package br.com.pedromonteiro.biblioteca.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
@Entity(name = "Autor")
@Table(name = "TBL_AUTORES", schema = "BIBLIOTECA", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "AUTOR", "NACIONALIDADE" })
})
public class AutorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false, columnDefinition = "BIGINT COMMENT 'Código do registro do autor'")
    private Long id;

    @Column(name = "AUTOR", nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'Nome do autor'")
    private String nome;

    @Column(name = "NACIONALIDADE", nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'Nacionalidade do autor'")
    private String nacionalidade;

    @OneToMany(mappedBy = "autor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<LivroEntity> livros;

}
