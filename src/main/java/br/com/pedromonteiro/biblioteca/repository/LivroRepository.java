package br.com.pedromonteiro.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pedromonteiro.biblioteca.model.LivroEntity;

public interface LivroRepository extends JpaRepository<LivroEntity, Long> {

}
