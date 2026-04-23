package com.openchapter.openchapter.service;

import com.openchapter.openchapter.repository.LivroRepository;
import com.openchapter.openchapter.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class RelatorioService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    public Map<String, Object> obterEstatisticasGerais() {
        Map<String, Object> stats = new HashMap<>();

        stats.put("totalLivros", livroRepository.count());
        stats.put("reservasAtivas", reservaRepository.findByStatus("ATIVA").size());
        stats.put("reservasConcluidas", reservaRepository.findByStatus("CONCLUIDA").size());
        stats.put("reservasCanceladas", reservaRepository.findByStatus("CANCELADA").size());

        long total = livroRepository.count();
        stats.put("disponibilidade", total > 0 ? "Alta" : "Nenhum livro cadastrado");

        return stats;
    }
}