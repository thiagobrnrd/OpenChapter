package com.openchapter.openchapter.controller;

import com.openchapter.openchapter.service.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/relatorios")
public class RelatorioController {

    @Autowired
    private RelatorioService relatorioService;

    @GetMapping
    public String exibirDashboard(Model model) {
        model.addAttribute("stats", relatorioService.obterEstatisticasGerais());
        return "relatorios/dashboard";
    }
}