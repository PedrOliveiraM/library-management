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
@Entity(name = "Categoria")
@Table(name = "TBL_CATEGORIAS", schema = "BIBLIOTECA")
public class CategoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false, columnDefinition = "BIGINT COMMENT 'Código do registro'")
    private Long id;

    @Column(name = "AUTOR", unique = true, nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'Nome do autor'")
    private String nome;

}