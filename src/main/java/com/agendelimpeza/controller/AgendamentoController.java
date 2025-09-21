package com.agendelimpeza.controller;

import com.agendelimpeza.model.Agendamento;
import com.agendelimpeza.repository.AgendamentoRepository;
import com.agendelimpeza.repository.ProfissionalRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/agendamentos")
public class AgendamentoController {

    private final AgendamentoRepository repository;
    private final ProfissionalRepository repositoryProfissional;

    public AgendamentoController(AgendamentoRepository repository, ProfissionalRepository repositoryProfissional) {
        this.repository = repository;
        this.repositoryProfissional = repositoryProfissional;
    }

    @GetMapping
    @ResponseBody
    public List<Agendamento> listar() {
        return repository.findAll();
    }

    @GetMapping("/lista")
    public String listarAgendamentos(Model model) {
        model.addAttribute("agendamentos", repository.findAll());
        return "lista";
    }

    @GetMapping("/novo")
    public String mostrarFormulario(@RequestParam(required = false) Long profissional, Model model) {
        Agendamento agendamento = new Agendamento();
        if (profissional != null) {
            var prof = repositoryProfissional.findById(profissional)
                    .orElseThrow(() -> new IllegalArgumentException("Profissional não encontrado"));
            agendamento.setProfissional(prof);
            model.addAttribute("profissionalSelecionado", prof);
        }
        model.addAttribute("agendamento", agendamento);
        return "formulario";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Agendamento agendamento) {
        repository.save(agendamento);
        return "redirect:/agendamentos/lista";
    }

    @GetMapping("/editar/{id}")
    public String editarAgendamento(@PathVariable Long id, Model model) {
        Agendamento agendamento = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        model.addAttribute("agendamento", agendamento);
        return "formulario";
    }

    @PostMapping("/atualizar/{id}")
    public String atualizarAgendamento(@PathVariable Long id, @ModelAttribute Agendamento agendamento) {
        agendamento.setId(id);
        repository.save(agendamento);
        return "redirect:/agendamentos/lista";
    }

    @GetMapping("/excluir/{id}")
    public String excluirAgendamento(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/agendamentos/lista";
    }
}
