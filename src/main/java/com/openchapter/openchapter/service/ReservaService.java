package com.openchapter.openchapter.service;

import com.openchapter.openchapter.model.Livro;
import com.openchapter.openchapter.model.Reserva;
import com.openchapter.openchapter.repository.LivroRepository;
import com.openchapter.openchapter.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private LivroRepository livroRepository;

    public List<Reserva> listarTodas() {
        return reservaRepository.findAll();
    }

    public Reserva buscarPorId(String id) {
        return reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva não encontrada!"));
    }

    public Reserva realizar(String livroId, String usuarioEmail) {
        Livro livro = livroRepository.findById(livroId)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado!"));

        Reserva reserva = new Reserva();
        reserva.setLivroId(livroId);
        reserva.setLivroTitulo(livro.getTitulo());
        reserva.setUsuarioEmail(usuarioEmail);
        reserva.setDataReserva(LocalDate.now());
        reserva.setDataExpiracao(LocalDate.now().plusDays(7));
        reserva.setStatus("ATIVA");

        return reservaRepository.save(reserva);
    }

    public Reserva cancelar(String id) {
        Reserva reserva = buscarPorId(id);
        if (!reserva.getStatus().equals("ATIVA")) {
            throw new RuntimeException("Esta reserva não pode ser cancelada!");
        }
        reserva.setStatus("CANCELADA");
        return reservaRepository.save(reserva);
    }
}