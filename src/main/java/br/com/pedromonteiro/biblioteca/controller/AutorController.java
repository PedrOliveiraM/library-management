package br.com.pedromonteiro.biblioteca.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.pedromonteiro.biblioteca.dto.AutorDto;
import br.com.pedromonteiro.biblioteca.service.AutorService;
import br.com.pedromonteiro.biblioteca.util.ApiResponse;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/autores")
public class AutorController {

    @Autowired
    private AutorService service;

    @PostMapping("incluir")
    @Transactional
    public ResponseEntity<ApiResponse<AutorDto>> createAuthor(@Valid @RequestBody AutorDto request) {
        AutorDto createdAuthor = this.service.createAuthor(request);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdAuthor.getId())
                .toUri();

        ApiResponse<AutorDto> response = new ApiResponse<>(201, createdAuthor, "Autor criado com sucesso");
        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("listar")
    public ResponseEntity<List<AutorDto>> getAllAuthors() {
        List<AutorDto> authors = this.service.getAllAuthors();
        return ResponseEntity.ok(authors);
    }

    @PutMapping("/alterar/{id}")
    @Transactional
    public ResponseEntity<ApiResponse<AutorDto>> updateAuthor(@PathVariable Long id, @RequestBody AutorDto request) {
        AutorDto updatedAuthor = this.service.updateAuthor(id, request);

        ApiResponse<AutorDto> response = new ApiResponse<>(200, updatedAuthor, "Autor atualizado com sucesso");
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<ApiResponse<AutorDto>> deleteAuthor(@PathVariable Long id) {
        AutorDto removedAuthor = this.service.removeAuthor(id);

        ApiResponse<AutorDto> response = new ApiResponse<>(200, removedAuthor, "Autor removido com sucesso");
        return ResponseEntity.ok().body(response);
    }
}
