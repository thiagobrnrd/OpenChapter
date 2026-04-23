package com.openchapter.openchapter.service;

import com.openchapter.openchapter.model.Emprestimo;
import com.openchapter.openchapter.model.Livro;
import com.openchapter.openchapter.repository.EmprestimoRepository;
import com.openchapter.openchapter.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private LivroRepository livroRepository;

    public List<Emprestimo> listarTodos() {
        return emprestimoRepository.findAll();
    }

    public List<Emprestimo> listarAtivos() {
        return emprestimoRepository.findByStatus("ATIVO");
    }

    public Emprestimo buscarPorId(String id) {
        return emprestimoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado!"));
    }

    public Emprestimo realizar(String livroId, String usuarioEmail) {
        Livro livro = livroRepository.findById(livroId)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado!"));

        if (livro.getQuantidadeDisponivel() <= 0) {
            throw new RuntimeException("Livro indisponível para empréstimo!");
        }

        livro.setQuantidadeDisponivel(livro.getQuantidadeDisponivel() - 1);
        livroRepository.save(livro);

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setLivroId(livroId);
        emprestimo.setLivroTitulo(livro.getTitulo());
        emprestimo.setUsuarioEmail(usuarioEmail);
        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setDataDevolucaoPrevista(LocalDate.now().plusDays(14));
        emprestimo.setStatus("ATIVO");

        return emprestimoRepository.save(emprestimo);
    }

    public Emprestimo devolver(String id) {
        Emprestimo emprestimo = buscarPorId(id);

        if (!emprestimo.getStatus().equals("ATIVO")) {
            throw new RuntimeException("Este empréstimo já foi devolvido!");
        }

        Livro livro = livroRepository.findById(emprestimo.getLivroId())
                .orElseThrow(() -> new RuntimeException("Livro não encontrado!"));

        livro.setQuantidadeDisponivel(livro.getQuantidadeDisponivel() + 1);
        livroRepository.save(livro);

        emprestimo.setDataDevolucaoReal(LocalDate.now());
        emprestimo.setStatus("DEVOLVIDO");

        return emprestimoRepository.save(emprestimo);
    }
}