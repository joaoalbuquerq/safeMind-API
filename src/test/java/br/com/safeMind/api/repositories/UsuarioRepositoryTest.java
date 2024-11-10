package br.com.safeMind.api.repositories;

import br.com.safeMind.api.Util.UtilitiesTest;
import br.com.safeMind.api.usuario.model.Usuario;
import br.com.safeMind.api.usuario.repository.UsuarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @DisplayName("Deve salvar uma pessoa com sucesso caso os campos obrigatórios sejam informados")
    @Test
    void deveSalvarUmaPessoaComSucesso() {
        Usuario usuarioSalvo = usuarioRepository.save(UtilitiesTest.montarObjetoUsuarioValido());
        assertNotNull(usuarioSalvo);
        assertNotNull(usuarioSalvo.getId());
        assertTrue(!usuarioSalvo.getId().equals(""));
    }

    @DisplayName("Deve listar todos os usuários existentes")
    @Test
    void deveListarPessoas(){
        usuarioRepository.save(UtilitiesTest.montarObjetoUsuarioValido());
        usuarioRepository.save(UtilitiesTest.montarObjetoUsuarioValido());

        List<Usuario> listaUsuarios = usuarioRepository.findAll();

        assertNotNull(listaUsuarios);
        assertEquals(2, listaUsuarios.size());
    }
}
