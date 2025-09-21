package com.agendelimpeza.repository;

import com.agendelimpeza.model.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {
    List<Profissional> findByCidade(String cidade);
}
