package br.com.pedromonteiro.biblioteca.model;

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
@Table(name = "TBL_AUTORES", schema = "BIBLIOTECA")
public class AutorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false, columnDefinition = "BIGINT COMMENT 'Código do registro'")
    private Long id;

    @Column(name = "AUTOR", unique = true, nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'Nome do autor'")
    private String nome;

    @Column(name = "NACIONALIDADE", unique = true, nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'Nacionalidade do autor'")
    private String nacionalidade;

}
