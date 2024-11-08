package br.com.pedromonteiro.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.pedromonteiro.biblioteca.dto.LivroDto;
import br.com.pedromonteiro.biblioteca.model.LivroEntity;
import br.com.pedromonteiro.biblioteca.repository.LivroRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;

    public LivroEntity createBook(LivroDto dto) {
        LivroEntity bookEntity = LivroEntity.builder()
                .titulo(dto.getTitulo())
                .isbn(dto.getIsbn())
                .autor_id(dto.getAutor_id())
                .categoria_id(dto.getCategoria_id())
                .build();

        return repository.save(bookEntity);
    }

    public List<LivroEntity> getAllBooks() {
        return repository.findAll();
    }

    public LivroEntity updateBook(Long id, LivroDto dto) {
        LivroEntity book = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado com id: " + id));

        if (dto.getTitulo() != null) {
            book.setTitulo(dto.getTitulo());
        }
        if (dto.getIsbn() != null) {
            book.setIsbn(dto.getIsbn());
        }
        if (dto.getAutor_id() != null) {
            book.setAutor_id(dto.getAutor_id());
        }
        if (dto.getCategoria_id() != null) {
            book.setCategoria_id(dto.getCategoria_id());
        }

        return repository.save(book);

    }

    public ResponseEntity<LivroEntity> removeBook(Long id) {
        LivroEntity removedBook = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado com id: " + id));

        repository.deleteById(id);

        return ResponseEntity.ok().body(removedBook);
    }
}
