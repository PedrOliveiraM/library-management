package br.com.pedromonteiro.biblioteca.dto;

public class LivroDto {

    private Long id;
    private String titulo;
    private String isbn;
    private Long autor_id;
    private Long categoria_id;
    
    public LivroDto() {
    }

    public LivroDto(Long id, String titulo, String isbn, Long autor_id, Long categoria_id) {
        this.id = id;
        this.titulo = titulo;
        this.isbn = isbn;
        this.autor_id = autor_id;
        this.categoria_id = categoria_id;
    }

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
