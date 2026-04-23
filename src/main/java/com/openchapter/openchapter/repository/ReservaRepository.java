package com.openchapter.openchapter.repository;

import com.openchapter.openchapter.model.Reserva;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ReservaRepository extends MongoRepository<Reserva, String> {
    List<Reserva> findByUsuarioEmail(String email);

    List<Reserva> findByStatus(String status);

    List<Reserva> findByLivroId(String livroId);
}