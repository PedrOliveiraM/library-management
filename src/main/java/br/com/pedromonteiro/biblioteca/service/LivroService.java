package br.com.pedromonteiro.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pedromonteiro.biblioteca.dto.LivroDto;
import br.com.pedromonteiro.biblioteca.dto.LivroResponseDto;
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

    public LivroResponseDto createBook(LivroDto dto) {
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

        LivroEntity savedBook = bookRepository.save(livro);
        return convertToDto(savedBook);
    }

    public List<LivroResponseDto> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    public LivroResponseDto updateBook(Long id, LivroDto dto) {
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

        LivroEntity updatedBook = bookRepository.save(book);
        return convertToDto(updatedBook);
    }

    public LivroResponseDto removeBook(Long id) {
        LivroEntity removedBook = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado com id: " + id));

        bookRepository.deleteById(id);
        return convertToDto(removedBook);
    }

    private LivroResponseDto convertToDto(LivroEntity bookEntity) {
        LivroResponseDto dto = new LivroResponseDto();
        dto.setId(bookEntity.getId());
        dto.setTitulo(bookEntity.getTitulo());
        dto.setIsbn(bookEntity.getIsbn());
        dto.setAutor(bookEntity.getAutor());
        dto.setCategoria(bookEntity.getCategoria());
        return dto;
    }
}
