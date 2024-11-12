package br.com.safeMind.api.Util;

import br.com.safeMind.api.usuario.model.Usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class UtilitiesTest {

    public static Usuario montarObjetoUsuarioValido(){
        Usuario usuario = new Usuario();
        usuario.setNome("pedro");
        usuario.setEmail("pedro@email.com");
        usuario.setCpf("129.655.344-23");
        usuario.setDataNascimento(LocalDate.now());
        usuario.setDataCriacao(LocalDateTime.now());

        return usuario;
    }

    public static Usuario montarObjetoUsuarioComId(){
        Usuario usuario = new Usuario();
        usuario.setId(UUID.randomUUID());
        usuario.setNome("pedro");
        usuario.setEmail("pedro@email.com");
        usuario.setCpf("129.655.344-23");
        usuario.setDataNascimento(LocalDate.now());
        usuario.setDataCriacao(LocalDateTime.now());

        return usuario;
    }

}
