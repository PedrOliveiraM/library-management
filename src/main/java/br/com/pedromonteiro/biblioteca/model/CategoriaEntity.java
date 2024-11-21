package br.com.pedromonteiro.biblioteca.model;

import java.util.List;

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
    @Column(name = "ID", unique = true, nullable = false, columnDefinition = "BIGINT COMMENT 'Código do registro da categoria'")
    private Long id;

    @Column(name = "NOME", nullable = false, unique = true, columnDefinition = "VARCHAR(255) COMMENT 'Nome da categoria'")
    private String nome;
}
