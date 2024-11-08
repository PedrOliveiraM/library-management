package br.com.pedromonteiro.biblioteca.dto;

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

public class LivroDto {

    private Long id;
    private String titulo;
    private String isbn;
    private Long autor_id;
    private Long categoria_id;

}
