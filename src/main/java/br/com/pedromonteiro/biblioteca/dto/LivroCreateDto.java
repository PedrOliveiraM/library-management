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

public class LivroCreateDto {
    
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
