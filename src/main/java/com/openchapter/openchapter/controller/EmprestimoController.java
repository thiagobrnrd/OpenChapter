package com.openchapter.openchapter.controller;

import com.openchapter.openchapter.service.EmprestimoService;
import com.openchapter.openchapter.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @Autowired
    private LivroService livroService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("emprestimos", emprestimoService.listarTodos());
        return "emprestimos/lista";
    }

    @GetMapping("/novo")
    public String novoForm(Model model) {
        model.addAttribute("livros", livroService.listarTodos());
        return "emprestimos/form";
    }

    @PostMapping("/salvar")
    public String salvar(@RequestParam String livroId,
            Authentication authentication,
            Model model) {
        try {
            emprestimoService.realizar(livroId, authentication.getName());
            return "redirect:/emprestimos?sucesso";
        } catch (RuntimeException e) {
            model.addAttribute("erro", e.getMessage());
            model.addAttribute("livros", livroService.listarTodos());
            return "emprestimos/form";
        }
    }

    @PostMapping("/devolver/{id}")
    public String devolver(@PathVariable String id) {
        emprestimoService.devolver(id);
        return "redirect:/emprestimos?devolvido";
    }
}