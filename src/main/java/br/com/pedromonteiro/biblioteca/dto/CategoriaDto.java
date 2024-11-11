package br.com.pedromonteiro.biblioteca.dto;

import org.hibernate.validator.constraints.Length;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

public class CategoriaDto {

    private Long id;

    @NotBlank(message = "A categoria não pode estar em branco")
    private String nome;

}
