package br.com.pedromonteiro.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pedromonteiro.biblioteca.model.AutorEntity;

public interface AutorRepository extends JpaRepository<AutorEntity, Long> {

}
