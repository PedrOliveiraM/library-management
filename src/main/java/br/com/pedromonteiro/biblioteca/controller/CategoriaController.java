package br.com.pedromonteiro.biblioteca.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.pedromonteiro.biblioteca.dto.CategoriaDto;
import br.com.pedromonteiro.biblioteca.model.CategoriaEntity;
import br.com.pedromonteiro.biblioteca.service.CategoriaService;
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
@RequestMapping("/api/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @PostMapping("incluir")
    @Transactional
    public ResponseEntity<CategoriaEntity> createCategory(@Valid @RequestBody CategoriaDto request) {
        CategoriaEntity CategoryEntity = this.service.createCategory(request);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(CategoryEntity.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("listar")
    public ResponseEntity<List<CategoriaEntity>> getAllCategorys() {
        List<CategoriaEntity> Categories = this.service.getAllCategories();
        return ResponseEntity.ok(Categories);
    }

    @PutMapping("/alterar/{id}")
    @Transactional
    public ResponseEntity<CategoriaEntity> updateCategory(@PathVariable Long id, @RequestBody CategoriaDto request) {
        CategoriaEntity updatedCategory = this.service.updateCategory(id, request);
        return ResponseEntity.ok().body(updatedCategory);
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<CategoriaEntity> deleteCategory(@PathVariable Long id) {
        ResponseEntity<CategoriaEntity> removedCategory = this.service.removeCategory(id);
        return removedCategory;
    }
}