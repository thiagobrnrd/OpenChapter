package com.openchapter.openchapter.repository;

import com.openchapter.openchapter.model.Livro;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface LivroRepository extends MongoRepository<Livro, String> {
    List<Livro> findByTituloContainingIgnoreCase(String titulo);

    List<Livro> findByAutorContainingIgnoreCase(String autor);

    List<Livro> findByCategoria(String categoria);

    boolean existsByIsbn(String isbn);
}