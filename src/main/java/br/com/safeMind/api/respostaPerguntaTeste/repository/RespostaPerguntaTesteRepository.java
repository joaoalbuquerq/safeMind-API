package br.com.safeMind.api.respostaPerguntaTeste.repository;

import br.com.safeMind.api.respostaPerguntaTeste.model.RespostaPerguntaTeste;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RespostaPerguntaTesteRepository extends JpaRepository<RespostaPerguntaTeste, UUID> {
}
