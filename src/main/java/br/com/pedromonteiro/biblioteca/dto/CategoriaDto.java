package br.com.pedromonteiro.biblioteca.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoriaDto {

    private Long id;

    @NotBlank(message = "A categoria não pode estar em branco")
    private String nome;

}
