package br.com.safeMind.api.pergunta.repository;

import br.com.safeMind.api.pergunta.model.Pergunta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PerguntaRepository extends JpaRepository<Pergunta, UUID> {
    List<Pergunta> findByTesteId(UUID id);
}
