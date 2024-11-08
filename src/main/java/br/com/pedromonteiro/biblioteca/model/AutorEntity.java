package br.com.pedromonteiro.biblioteca.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "TBL_AUTORES", schema = "BIBLIOTECA")
public class AutorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = true, columnDefinition = "BIGINT COMMENT 'Código do registro'")
    private Long id;

    @Column(name = "AUTOR", unique = true, nullable = true, columnDefinition = "VARCHAR(255) COMMENT 'Nome do autor'")
    private String nome;

    @Column(name = "NACIONALIDADE", unique = true, nullable = true, columnDefinition = "VARCHAR(255) COMMENT 'Nacionalidade do autor'")
    private String nacionalidade;

    @OneToMany(mappedBy = "autor", fetch = FetchType.LAZY)
    private List<LivroEntity> livros;
}
