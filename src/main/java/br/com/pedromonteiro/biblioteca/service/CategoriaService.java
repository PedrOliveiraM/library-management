package br.com.pedromonteiro.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.pedromonteiro.biblioteca.dto.CategoriaDto;
import br.com.pedromonteiro.biblioteca.model.CategoriaEntity;
import br.com.pedromonteiro.biblioteca.repository.CategoriaRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public CategoriaEntity createCategory(CategoriaDto dto) {
        CategoriaEntity CategoryEntity = CategoriaEntity.builder()
                .nome(dto.getNome())
                .build();

        return repository.save(CategoryEntity);
    }

    public List<CategoriaEntity> getAllCategories() {
        return repository.findAll();
    }

    public CategoriaEntity updateCategory(Long id, CategoriaDto dto) {
        CategoriaEntity Category = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrado com id: " + id));

        if (dto.getNome() != null) {
            Category.setNome(dto.getNome());
        }

        return repository.save(Category);

    }

    public ResponseEntity<CategoriaEntity> removeCategory(Long id) {
        CategoriaEntity removedCategory = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrado com id: " + id));

        repository.deleteById(id);

        return ResponseEntity.ok().body(removedCategory);
    }
}
