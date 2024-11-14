package br.com.safeMind.api.teste.repository;

import br.com.safeMind.api.teste.model.Teste;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TesteRepository extends JpaRepository<Teste, UUID> {
}
