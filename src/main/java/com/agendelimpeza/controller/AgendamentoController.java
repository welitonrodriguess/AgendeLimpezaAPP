// Pacote: com.agendelimpeza.controller
package com.agendelimpeza.controller;

import com.agendelimpeza.model.Agendamento; // Supondo que você tenha essa classe
import com.agendelimpeza.repository.AgendamentoRepository; // Supondo que você tenha esse repositório
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agendamentos") // Padrão de URL para sua API
public class AgendamentoController {

    @Autowired
    private AgendamentoRepository repository;

    @GetMapping
    public List<Agendamento> listarTodos() {
        return repository.findAll();
    }

    @PostMapping
    public Agendamento criar(@RequestBody Agendamento agendamento) {
        return repository.save(agendamento);
    }
}