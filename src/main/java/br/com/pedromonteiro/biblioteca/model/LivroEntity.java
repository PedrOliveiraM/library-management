package br.com.pedromonteiro.biblioteca.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TBL_LIVROS", schema = "BIBLIOTECA")
public class LivroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODIGO")
    private Long id;

    @Column(name = "TITULO")
    private String titulo;

    @Column(name = "ISBN")
    private String isbn;

    // ManyToOne
    @Column(name = "COD_AUTOR")
    private Long autor_id;

    @Column(name = "COD_CATEGORIA")
    private Long categoria_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Long getAutor_id() {
        return autor_id;
    }

    public void setAutor_id(Long autor_id) {
        this.autor_id = autor_id;
    }

    public Long getCategoria_id() {
        return categoria_id;
    }

    public void setCategoria_id(Long categoria_id) {
        this.categoria_id = categoria_id;
    }
}
