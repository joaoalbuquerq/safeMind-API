package br.com.safeMind.api.usuario.repository;

import br.com.safeMind.api.usuario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
