package br.com.pedromonteiro.biblioteca.dto;

import br.com.pedromonteiro.biblioteca.model.AutorEntity;
import br.com.pedromonteiro.biblioteca.model.CategoriaEntity;
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
public class LivroResponseDto {
    private Long id;

    private String titulo;

    private String isbn;

    private AutorEntity autor;

    private CategoriaEntity categoria;
}
