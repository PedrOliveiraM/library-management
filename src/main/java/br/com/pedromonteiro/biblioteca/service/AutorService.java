package br.com.pedromonteiro.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.pedromonteiro.biblioteca.dto.AutorDto;
import br.com.pedromonteiro.biblioteca.model.AutorEntity;
import br.com.pedromonteiro.biblioteca.repository.AutorRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class AutorService {

    @Autowired
    private AutorRepository repository;

    public AutorEntity createAuthor(AutorDto dto) {
        AutorEntity authorEntity = AutorEntity.builder()
                .nome(dto.getNome())
                .nacionalidade(dto.getNacionalidade())
                .build();

        return repository.save(authorEntity);

    }

    public List<AutorEntity> getAllAuthor() {
        return repository.findAll();

    }

    public AutorEntity updateAuthor(Long id, AutorDto dto) {
        AutorEntity author = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Autor não encontrado com id: " + id));

        if (dto.getNome() != null) {
            author.setNome(dto.getNome());
        }
        if (dto.getNacionalidade() != null) {
            author.setNacionalidade(dto.getNacionalidade());
        }

        return repository.save(author);

    }

    public ResponseEntity<AutorEntity> removeAuthor(Long id) {
        AutorEntity removedAuthor = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Autor não encontrado com id: " + id));

        repository.deleteById(id);

        return ResponseEntity.ok().body(removedAuthor);

    }
}
