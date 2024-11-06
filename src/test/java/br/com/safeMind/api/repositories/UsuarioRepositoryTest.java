package br.com.safeMind.api.repositories;

import br.com.safeMind.api.usuario.model.Usuario;
import br.com.safeMind.api.usuario.repository.UsuarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @DisplayName("Deve salvar uma pessoa com sucesso caso os campos obrigatórios sejam informados")
    @Test
    void deveSalvarUmaPessoaComSucesso() {
        Usuario usuario = new Usuario();
        usuario.setNome("Joao");
        usuario.setEmail("joao@email.com");
        usuario.setCpf("129.655.344-23");
        usuario.setDataNascimento(LocalDate.now());
        usuario.setDataCriacao(LocalDateTime.now());

        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        assertNotNull(usuarioSalvo);
        assertNotNull(usuarioSalvo.getId());
        assertTrue(!usuarioSalvo.getId().equals(""));
    }
}
