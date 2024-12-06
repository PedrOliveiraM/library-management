package br.com.pedromonteiro.biblioteca.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AutorDto {

    private Long id;

    @NotBlank(message = "O nome do autor não pode estar em branco")
    private String nome;

    @NotBlank(message = "A nacionalidade do autor não pode estar em branco")
    private String nacionalidade;

}
