package br.com.pedromonteiro.biblioteca.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.pedromonteiro.biblioteca.dto.LivroDto;
import br.com.pedromonteiro.biblioteca.model.LivroEntity;
import br.com.pedromonteiro.biblioteca.service.LivroService;
import jakarta.validation.Valid;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService service;

    @GetMapping("")
    public ResponseEntity<List<LivroEntity>> getAllBooks() {
        List<LivroEntity> books = this.service.getAllBooks();

        return ResponseEntity.ok(books);
    }

    @PostMapping("")
    public ResponseEntity<LivroEntity> createBook(@Valid @RequestBody LivroDto request) {
        LivroEntity bookEntity = this.service.createBook(request);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(bookEntity.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

}