package br.com.pedromonteiro.biblioteca.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.pedromonteiro.biblioteca.dto.LivroDto;
import br.com.pedromonteiro.biblioteca.dto.LivroResponseDto;
import br.com.pedromonteiro.biblioteca.service.LivroService;
import br.com.pedromonteiro.biblioteca.util.ApiResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/livros")
public class LivroController {

    @Autowired
    private LivroService service;

    @PostMapping("incluir")
    @Transactional
    public ResponseEntity<ApiResponse<LivroResponseDto>> createBook(@Valid @RequestBody LivroDto request) {
        LivroResponseDto bookDto = this.service.createBook(request);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(bookDto.getId())
                .toUri();

        ApiResponse<LivroResponseDto> response = new ApiResponse<>(201, bookDto, "Livro criado com sucesso");
        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("listar")
    public ResponseEntity<List<LivroResponseDto>> getAllBooks() {
        List<LivroResponseDto> books = this.service.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @PutMapping("/alterar/{id}")
    @Transactional
    public ResponseEntity<ApiResponse<LivroResponseDto>> updateBook(@PathVariable Long id, @RequestBody LivroDto request) {
        LivroResponseDto updatedBookDto = this.service.updateBook(id, request);

        ApiResponse<LivroResponseDto> response = new ApiResponse<>(200, updatedBookDto, "Livro atualizado com sucesso");
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<ApiResponse<LivroResponseDto>> deleteBook(@PathVariable Long id) {
        LivroResponseDto removedBookDto = this.service.removeBook(id);

        ApiResponse<LivroResponseDto> response = new ApiResponse<>(200, removedBookDto, "Livro removido com sucesso");
        return ResponseEntity.ok().body(response);
    }
}
