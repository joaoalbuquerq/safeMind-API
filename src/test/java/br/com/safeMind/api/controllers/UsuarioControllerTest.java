package br.com.safeMind.api.controllers;

import br.com.safeMind.api.Util.UtilitiesTest;
import br.com.safeMind.api.usuario.controller.UsuarioController;
import br.com.safeMind.api.usuario.dto.UsuarioCadastroDTO;
import br.com.safeMind.api.usuario.model.Usuario;
import br.com.safeMind.api.usuario.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UsuarioService usuarioService;

    @DisplayName("Deve retornar 201 quando um usuário for cadastrado corretamente")
    @Test
    void deveRetornar201QuandoForCriadoUsuarioComSucesso() throws Exception {

        UsuarioCadastroDTO dto = new UsuarioCadastroDTO("JOAO","12965534423","JOAO@GMAIL.COM", LocalDate.of(2020,5,20));

        String dtoJson = objectMapper.writeValueAsString(dto);

        Usuario usuarioSalvo = new Usuario(dto);
        when(usuarioService.cadastrar(dto)).thenReturn(usuarioSalvo);

        mockMvc.perform(post("/usuario")
                        .contentType("application/json")
                        .content(dtoJson))
                .andExpect(status().isCreated());
    }

    @DisplayName("Deve retornar 200 quando um usuário for pesquisado por id e encontrado")
    @Test
    void deveRetornar200QuandoForPesquisadoUmUsuarioPeloIdComSucesso() throws Exception {
        UUID idAleatorio = UUID.randomUUID();
        var usuario = UtilitiesTest.montarObjetoUsuarioComId();
        when(usuarioService.pesquisarPorId(idAleatorio)).thenReturn(usuario);
        mockMvc.perform(get("/usuario/" + idAleatorio)).andExpect(status().isOk());
    }

    @DisplayName("Deve retornar 200 quando um usuário for pesquisado por cpf e encontrado")
    @Test
    void deveRetornar200QuandoForPesquisadoPeloCpfEencontrado() throws Exception {
        var usuario = UtilitiesTest.montarObjetoUsuarioComId();
        when(usuarioService.pesquisarPorCpf(usuario.getCpf())).thenReturn(usuario);
        mockMvc.perform(get("/usuario/cpf/" + usuario.getCpf())).andExpect(status().isOk());
    }

    @DisplayName("Deve retornar 404 quando não for encontrado um usuário")
    @Test
    void deveRetornar404QuandoForEncontradoUmUsuario() throws Exception {
        var usuario = UtilitiesTest.montarObjetoUsuarioComId();
        when(usuarioService.pesquisarPorCpf(usuario.getCpf())).thenThrow(new EntityNotFoundException());

        mockMvc.perform(get("/usuario/cpf/" + usuario.getCpf())).andExpect(status().isNotFound());
    }
}
