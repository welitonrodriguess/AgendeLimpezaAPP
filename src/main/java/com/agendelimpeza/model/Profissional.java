package com.agendelimpeza.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Profissional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cidade;
    private Double valorHora;

    @ElementCollection
    private List<String> horariosDisponiveis;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }

    public Double getValorHora() { return valorHora; }
    public void setValorHora(Double valorHora) { this.valorHora = valorHora; }

    public List<String> getHorariosDisponiveis() { return horariosDisponiveis; }
    public void setHorariosDisponiveis(List<String> horariosDisponiveis) { this.horariosDisponiveis = horariosDisponiveis; }
}
