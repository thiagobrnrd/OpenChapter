package com.openchapter.openchapter.controller;

import com.openchapter.openchapter.service.LivroService;
import com.openchapter.openchapter.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private LivroService livroService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("reservas", reservaService.listarTodas());
        return "reservas/lista";
    }

    @GetMapping("/nova")
    public String novaForm(Model model) {
        model.addAttribute("livros", livroService.listarTodos());
        return "reservas/form";
    }

    @PostMapping("/salvar")
    public String salvar(@RequestParam String livroId,
            Authentication authentication,
            Model model) {
        try {
            reservaService.realizar(livroId, authentication.getName());
            return "redirect:/reservas?sucesso";
        } catch (RuntimeException e) {
            model.addAttribute("erro", e.getMessage());
            model.addAttribute("livros", livroService.listarTodos());
            return "reservas/form";
        }
    }

    @PostMapping("/cancelar/{id}")
    public String cancelar(@PathVariable String id) {
        reservaService.cancelar(id);
        return "redirect:/reservas?cancelado";
    }
}