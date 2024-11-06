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

    public LivroEntity incluir(LivroDto dto) {
    }

}
