package br.com.pedromonteiro.biblioteca.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

import br.com.pedromonteiro.biblioteca.dto.AutorDto;
import br.com.pedromonteiro.biblioteca.model.AutorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.pedromonteiro.biblioteca.repository.AutorRepository;

@Service
public class AutorService {

    @Autowired
    private AutorRepository repository;

    public AutorDto createAuthor(AutorDto dto) {
        AutorEntity authorEntity = AutorEntity.builder()
                .nome(dto.getNome())
                .nacionalidade(dto.getNacionalidade())
                .build();

        AutorEntity savedAuthor = repository.save(authorEntity);
        return mapToDto(savedAuthor);
    }

    public List<AutorDto> getAllAuthors() {
        return repository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public AutorDto updateAuthor(Long id, AutorDto dto) {
        AutorEntity author = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Autor não encontrado com id: " + id));

        if (dto.getNome() != null) {
            author.setNome(dto.getNome());
        }
        if (dto.getNacionalidade() != null) {
            author.setNacionalidade(dto.getNacionalidade());
        }

        AutorEntity updatedAuthor = repository.save(author);
        return mapToDto(updatedAuthor);
    }

    public AutorDto removeAuthor(Long id) {
        AutorEntity removedAuthor = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Autor não encontrado com id: " + id));

        repository.deleteById(id);
        return mapToDto(removedAuthor);
    }

    private AutorDto mapToDto(AutorEntity entity) {
        return AutorDto.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .nacionalidade(entity.getNacionalidade())
                .build();
    }
}
