package br.com.safeMind.api.usuario.repository;

import br.com.safeMind.api.usuario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    Optional<Usuario> findByCpf(String cpf);
}
