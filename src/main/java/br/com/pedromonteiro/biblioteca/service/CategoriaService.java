package br.com.pedromonteiro.biblioteca.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

import br.com.pedromonteiro.biblioteca.dto.CategoriaDto;
import br.com.pedromonteiro.biblioteca.model.CategoriaEntity;
import br.com.pedromonteiro.biblioteca.repository.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public CategoriaDto createCategory(CategoriaDto dto) {
        CategoriaEntity categoryEntity = CategoriaEntity.builder()
                .nome(dto.getNome())
                .build();

        CategoriaEntity savedCategory = repository.save(categoryEntity);
        return mapToDto(savedCategory);
    }

    public List<CategoriaDto> getAllCategories() {
        List<CategoriaEntity> categories = repository.findAll();
        return categories.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public CategoriaDto updateCategory(Long id, CategoriaDto dto) {
        CategoriaEntity category = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada com id: " + id));

        if (dto.getNome() != null) {
            category.setNome(dto.getNome());
        }

        CategoriaEntity updatedCategory = repository.save(category);
        return mapToDto(updatedCategory);
    }

    public void removeCategory(Long id) {
        CategoriaEntity category = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada com id: " + id));

        repository.delete(category);
    }

    private CategoriaDto mapToDto(CategoriaEntity entity) {
        return CategoriaDto.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .build();
    }
}
