package com.agendelimpeza.controller;

import com.agendelimpeza.model.Profissional;
import com.agendelimpeza.repository.ProfissionalRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/profissionais")
public class ProfissionalController {

    private final ProfissionalRepository repository;

    public ProfissionalController(ProfissionalRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{cidade}")
    public String listarPorCidade(@PathVariable String cidade, Model model) {
        List<Profissional> profissionais = repository.findByCidade(cidade);
        model.addAttribute("profissionais", profissionais);
        model.addAttribute("cidade", cidade);
        return "profissionais";
    }

    @PostMapping("/cadastrar")
    @ResponseBody
    public Profissional cadastrar(@RequestBody Profissional profissional) {
        return repository.save(profissional);
    }
}
