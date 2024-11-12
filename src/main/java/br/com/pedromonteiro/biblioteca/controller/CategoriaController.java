package br.com.pedromonteiro.biblioteca.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.pedromonteiro.biblioteca.dto.CategoriaDto;
import br.com.pedromonteiro.biblioteca.model.CategoriaEntity;
import br.com.pedromonteiro.biblioteca.service.CategoriaService;
import br.com.pedromonteiro.biblioteca.util.ApiResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @PostMapping("incluir")
    @Transactional
    public ResponseEntity<ApiResponse<CategoriaEntity>> createCategory(@Valid @RequestBody CategoriaDto request) {
        CategoriaEntity createdCategory = this.service.createCategory(request);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdCategory.getId())
                .toUri();

        ApiResponse<CategoriaEntity> response = new ApiResponse<>(201, createdCategory, "Categoria criada com sucesso");
        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("listar")
    public ResponseEntity<List<CategoriaEntity>> getAllCategories() {
        List<CategoriaEntity> categories = this.service.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @PutMapping("/alterar/{id}")
    @Transactional
    public ResponseEntity<ApiResponse<CategoriaEntity>> updateCategory(@PathVariable Long id,
            @RequestBody CategoriaDto request) {
        CategoriaEntity updatedCategory = this.service.updateCategory(id, request);

        ApiResponse<CategoriaEntity> response = new ApiResponse<>(200, updatedCategory,
                "Categoria atualizada com sucesso");
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<ApiResponse<CategoriaEntity>> deleteCategory(@PathVariable Long id) {
        CategoriaEntity removedCategory = this.service.removeCategory(id);

        ApiResponse<CategoriaEntity> response = new ApiResponse<>(200, removedCategory,
                "Categoria removida com sucesso");
        return ResponseEntity.ok().body(response);
    }
}