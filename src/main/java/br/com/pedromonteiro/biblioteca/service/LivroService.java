package br.com.pedromonteiro.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pedromonteiro.biblioteca.dto.LivroDto;
import br.com.pedromonteiro.biblioteca.model.LivroEntity;
import br.com.pedromonteiro.biblioteca.repository.LivroRepository;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;

    public List<LivroEntity> getAllBooks() {
        return repository.findAll();
    }

    public LivroEntity createBook(LivroDto dto) {
        LivroEntity bookEntity = LivroEntity.builder()
                .titulo(dto.getTitulo())
                .isbn(dto.getIsbn())
                .autor_id(dto.getAutor_id())
                .categoria_id(dto.getCategoria_id())
                .build();

        return repository.save(bookEntity);
    }
}
