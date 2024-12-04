package br.com.pedromonteiro.biblioteca.dto;

import org.hibernate.validator.constraints.Length;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class LivroDto {

    private Long id;

    @NotBlank(message = "O título do livro não pode estar em branco")
    private String titulo;

    @NotBlank(message = "O ISBN não pode estar em branco")
    @Length(min = 13, max = 13, message = "O ISBN deve ter exatamente 13 caracteres")
    private String isbn;

    @NotNull(message = "O ID do autor não pode ser nulo")
    private Long autor_id;

    @NotNull(message = "O ID da categoria não pode ser nulo")
    private Long categoria_id;
}
