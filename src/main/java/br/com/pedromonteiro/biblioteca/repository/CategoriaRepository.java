package br.com.pedromonteiro.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pedromonteiro.biblioteca.model.CategoriaEntity;

public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long> {

}
