package br.com.safeMind.api.repositories;

import br.com.safeMind.api.Util.UtilitiesTest;
import br.com.safeMind.api.usuario.dto.UsuarioAtualizacaoDTO;
import br.com.safeMind.api.usuario.model.Usuario;
import br.com.safeMind.api.usuario.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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

    @DisplayName("Deve obter um usuário pelo seu id")
    @Test
    void deveObterUmUsuarioPeloSeuId() {
        var userExistente = usuarioRepository.save(UtilitiesTest.montarObjetoUsuarioValido());

        var usuario = usuarioRepository.findById(userExistente.getId());
        assertNotNull(usuario.get());
        assertNotNull(usuario.get().getId());
    }

    @DisplayName("Deve retornar excessão de objeto não encontrado")
    @Test
    void deveRetornarExcessoDeUsuarioNaoEncontrado() {
        assertThrows(EntityNotFoundException.class, () -> {
            usuarioRepository.findById(UUID.randomUUID()).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        });
    }

    @DisplayName("Dete atualizar um usuário existente")
    @Test
    void deteAtualizarUmUsuarioExistente() {
        var user = UtilitiesTest.montarObjetoUsuarioValido();
        var nomeAntigo = user.getNome();
        var emailAntigo = user.getEmail();
        var ultimaAlteracao = user.getUltimaAlteracao();

        var dto = new UsuarioAtualizacaoDTO("almeida", "almeida@gmail.com");
        user.atualizarDados(dto);
        var usuarioAlterado = usuarioRepository.save(user);

        assertNotEquals(nomeAntigo, usuarioAlterado.getNome());
        assertNotEquals(emailAntigo, usuarioAlterado.getEmail());
        assertNotNull(usuarioAlterado.getUltimaAlteracao());
        assertNotEquals(ultimaAlteracao, usuarioAlterado.getUltimaAlteracao());
    }

    @DisplayName("Deve deletar um usuário caso seja fornecido um ID existente")
    @Test
    void deveDeletarUmUsuarioPeloSeuId() {
        var user = usuarioRepository.save(UtilitiesTest.montarObjetoUsuarioValido());
        usuarioRepository.delete(user);
        var usuarioDeletado = usuarioRepository.findById(user.getId());
        assertTrue(usuarioDeletado.isEmpty());
    }

    @DisplayName("Deve pesquisar um usuário pelo seu CPF")
    @Test
    void devePesquisarUmUsuarioPeloSeuCPF() {
        var user = usuarioRepository.save(UtilitiesTest.montarObjetoUsuarioValido());
        var usuarioPesquisado = usuarioRepository.findByCpf(user.getCpf()).get();

        assertNotNull(usuarioPesquisado);
        assertNotNull(usuarioPesquisado.getId());
        assertNotNull(usuarioPesquisado.getCpf());
        assertEquals(usuarioPesquisado.getCpf(), user.getCpf());
    }

}
