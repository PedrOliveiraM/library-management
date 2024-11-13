package br.com.pedromonteiro.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pedromonteiro.biblioteca.dto.LivroDto;
import br.com.pedromonteiro.biblioteca.model.AutorEntity;
import br.com.pedromonteiro.biblioteca.model.CategoriaEntity;
import br.com.pedromonteiro.biblioteca.model.LivroEntity;
import br.com.pedromonteiro.biblioteca.repository.AutorRepository;
import br.com.pedromonteiro.biblioteca.repository.CategoriaRepository;
import br.com.pedromonteiro.biblioteca.repository.LivroRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class LivroService {

    @Autowired
    private LivroRepository bookRepository;

    @Autowired
    private AutorRepository authorRepository;

    @Autowired
    private CategoriaRepository categoryRepository;

    public LivroEntity createBook(LivroDto dto) {
        AutorEntity author = authorRepository.findById(dto.getAutor_id())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Autor não encontrado com ID: " + dto.getAutor_id()));

        CategoriaEntity category = categoryRepository.findById(dto.getCategoria_id())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Categoria não encontrada com ID: " + dto.getCategoria_id()));

        LivroEntity livro = LivroEntity.builder()
                .titulo(dto.getTitulo())
                .isbn(dto.getIsbn())
                .autor(author)
                .categoria(category)
                .build();

        return bookRepository.save(livro);
    }

    public List<LivroEntity> getAllBooks() {
        return bookRepository.findAll();
    }

    public LivroEntity updateBook(Long id, LivroDto dto) {
        LivroEntity book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado com id: " + id));

        if (dto.getTitulo() != null) {
            book.setTitulo(dto.getTitulo());
        }
        if (dto.getIsbn() != null) {
            book.setIsbn(dto.getIsbn());
        }

        if (dto.getAutor_id() != null) {
            if (!book.getAutor().getId().equals(dto.getAutor_id())) {
                AutorEntity autor = authorRepository.findById(dto.getAutor_id())
                        .orElseThrow(
                                () -> new EntityNotFoundException("Autor não encontrado com ID: " + dto.getAutor_id()));
                book.setAutor(autor);
            }

        }
        if (dto.getCategoria_id() != null) {
            if (!book.getCategoria().getId().equals(dto.getCategoria_id())) {
                CategoriaEntity categoria = categoryRepository.findById(dto.getCategoria_id())
                        .orElseThrow(() -> new EntityNotFoundException(
                                "Categoria não encontrada com ID: " + dto.getCategoria_id()));
                book.setCategoria(categoria);
            }
        }

        return bookRepository.save(book);

    }

    public LivroEntity removeBook(Long id) {
        LivroEntity removedBook = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado com id: " + id));

        bookRepository.deleteById(id);

        return removedBook;
    }
}
