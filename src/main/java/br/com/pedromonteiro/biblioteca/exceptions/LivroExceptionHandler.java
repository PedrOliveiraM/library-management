package br.com.pedromonteiro.biblioteca.exceptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;
import lombok.var;

@RestControllerAdvice
public class LivroExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleError404(EntityNotFoundException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DataErrorValidation>> handleError400(MethodArgumentNotValidException response) {
        var erros = response.getFieldErrors();

        List<DataErrorValidation> errorDetails = erros.stream()
                .map(DataErrorValidation::new)
                .toList();

        return ResponseEntity.badRequest().body(errorDetails);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleError409(DataIntegrityViolationException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", "Violação de integridade de dados: " + ex.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    private record DataErrorValidation(String field, String message) {

        public DataErrorValidation(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }

    }
}