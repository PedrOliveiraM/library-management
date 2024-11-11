package br.com.pedromonteiro.biblioteca.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.pedromonteiro.biblioteca.dto.AutorDto;
import br.com.pedromonteiro.biblioteca.model.AutorEntity;
import br.com.pedromonteiro.biblioteca.service.AutorService;
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
@RequestMapping("/api/autores")
public class AutorController {

    @Autowired
    private AutorService service;

    @PostMapping("incluir")
    @Transactional
    public ResponseEntity<AutorEntity> createAuthor(@Valid @RequestBody AutorDto request) {
        AutorEntity AuthorEntity = this.service.createAuthor(request);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(AuthorEntity.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("listar")
    public ResponseEntity<List<AutorEntity>> getAllAuthors() {
        List<AutorEntity> Authors = this.service.getAllAuthors();
        return ResponseEntity.ok(Authors);
    }

    @PutMapping("/alterar/{id}")
    @Transactional
    public ResponseEntity<AutorEntity> updateAuthor(@PathVariable Long id, @RequestBody AutorDto request) {
        AutorEntity updatedAuthor = this.service.updateAuthor(id, request);
        return ResponseEntity.ok().body(updatedAuthor);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<AutorEntity> deleteAuthor(@PathVariable Long id) {
        ResponseEntity<AutorEntity> removedAuthor = this.service.removeAuthor(id);
        return removedAuthor;
    }
}