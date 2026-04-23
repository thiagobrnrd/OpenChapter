package com.openchapter.openchapter.service;

import com.openchapter.openchapter.model.Livro;
import com.openchapter.openchapter.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public List<Livro> listarTodos() {
        return livroRepository.findAll();
    }

    public Livro buscarPorId(String id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado!"));
    }

    public List<Livro> buscarPorTitulo(String titulo) {
        return livroRepository.findByTituloContainingIgnoreCase(titulo);
    }

    public Livro salvar(Livro livro) {
        if (livro.getId() == null && livroRepository.existsByIsbn(livro.getIsbn())) {
            throw new RuntimeException("ISBN já cadastrado!");
        }
        if (livro.getId() == null) {
            livro.setQuantidadeDisponivel(livro.getQuantidadeTotal());
        }
        return livroRepository.save(livro);
    }

    public void excluir(String id) {
        livroRepository.deleteById(id);
    }
}