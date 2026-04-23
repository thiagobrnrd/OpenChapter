package com.openchapter.openchapter.controller;

import com.openchapter.openchapter.model.Livro;
import com.openchapter.openchapter.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @GetMapping
    public String listar(@RequestParam(required = false) String busca, Model model) {
        if (busca != null && !busca.isBlank()) {
            model.addAttribute("livros", livroService.buscarPorTitulo(busca));
            model.addAttribute("busca", busca);
        } else {
            model.addAttribute("livros", livroService.listarTodos());
        }
        return "livros/lista";
    }

    @GetMapping("/novo")
    public String novoForm(Model model) {
        model.addAttribute("livro", new Livro());
        return "livros/form";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Livro livro, Model model) {
        try {
            livroService.salvar(livro);
            return "redirect:/livros?sucesso";
        } catch (RuntimeException e) {
            model.addAttribute("erro", e.getMessage());
            return "livros/form";
        }
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable String id, Model model) {
        model.addAttribute("livro", livroService.buscarPorId(id));
        return "livros/form";
    }

    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable String id) {
        livroService.excluir(id);
        return "redirect:/livros?excluido";
    }
}