package br.com.pedromonteiro.biblioteca.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.pedromonteiro.biblioteca.dto.AutorDto;
import br.com.pedromonteiro.biblioteca.model.AutorEntity;
import br.com.pedromonteiro.biblioteca.service.AutorService;
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
@RequestMapping("/api/autores")
public class AutorController {

    @Autowired
    private AutorService service;

    @PostMapping("incluir")
    @Transactional
    public ResponseEntity<ApiResponse<AutorEntity>> createAuthor(@Valid @RequestBody AutorDto request) {
        AutorEntity authorEntity = this.service.createAuthor(request);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(authorEntity.getId())
                .toUri();

        ApiResponse<AutorEntity> response = new ApiResponse<>(201, authorEntity, "Autor criado com sucesso");
        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("listar")
    public ResponseEntity<List<AutorEntity>> getAllAuthors() {
        List<AutorEntity> authors = this.service.getAllAuthors();
        return ResponseEntity.ok(authors);
    }

    @PutMapping("/alterar/{id}")
    @Transactional
    public ResponseEntity<ApiResponse<AutorEntity>> updateAuthor(@PathVariable Long id, @RequestBody AutorDto request) {
        AutorEntity updatedAuthor = this.service.updateAuthor(id, request);

        ApiResponse<AutorEntity> response = new ApiResponse<AutorEntity>(200, updatedAuthor,
                "Autor atualizado com sucesso");
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<ApiResponse<AutorEntity>> deleteAuthor(@PathVariable Long id) {
        AutorEntity removedAuthor = this.service.removeAuthor(id);

        ApiResponse<AutorEntity> response = new ApiResponse<AutorEntity>(200, removedAuthor,
                "Autor removido com sucesso");
        return ResponseEntity.ok().body(response);
    }
}