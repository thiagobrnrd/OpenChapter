package com.openchapter.openchapter.repository;

import com.openchapter.openchapter.model.Emprestimo;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface EmprestimoRepository extends MongoRepository<Emprestimo, String> {
    List<Emprestimo> findByUsuarioEmail(String email);

    List<Emprestimo> findByStatus(String status);

    List<Emprestimo> findByLivroId(String livroId);
}