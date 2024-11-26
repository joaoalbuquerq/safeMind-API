package br.com.safeMind.api.resposta.repository;

import br.com.safeMind.api.resposta.model.Resposta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RespostaRepository extends JpaRepository<Resposta, UUID> {
}
