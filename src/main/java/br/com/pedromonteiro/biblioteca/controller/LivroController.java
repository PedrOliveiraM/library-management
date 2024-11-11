package br.com.pedromonteiro.biblioteca.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.pedromonteiro.biblioteca.dto.LivroDto;
import br.com.pedromonteiro.biblioteca.model.LivroEntity;
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
    public ResponseEntity<ApiResponse<LivroEntity>> createBook(@Valid @RequestBody LivroDto request) {
        LivroEntity bookEntity = this.service.createBook(request);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(bookEntity.getId())
                .toUri();

        ApiResponse<LivroEntity> response = new ApiResponse<>(201, bookEntity, "Livro criado com sucesso");
        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("listar")
    public ResponseEntity<List<LivroEntity>> getAllBooks() {
        List<LivroEntity> books = this.service.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @PutMapping("/alterar/{id}")
    @Transactional
    public ResponseEntity<ApiResponse<LivroEntity>> updateBook(@PathVariable Long id, @RequestBody LivroDto request) {
        System.out.println(id);
        LivroEntity updatedBook = this.service.updateBook(id, request);

        ApiResponse<LivroEntity> response = new ApiResponse<>(200, updatedBook, "Livro atualizado com sucesso");
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<ApiResponse<LivroEntity>> deleteBook(@PathVariable Long id) {
        LivroEntity removedBook = this.service.removeBook(id);

        ApiResponse<LivroEntity> response = new ApiResponse<>(200, removedBook, "Livro removido com sucesso");
        return ResponseEntity.ok().body(response);
    }
}