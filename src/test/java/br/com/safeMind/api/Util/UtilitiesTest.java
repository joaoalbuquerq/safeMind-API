package br.com.safeMind.api.Util;

import br.com.safeMind.api.usuario.model.Usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UtilitiesTest {

    public static Usuario montarObjetoUsuarioValido(){
        Usuario usuario = new Usuario();
        usuario.setNome("Joao");
        usuario.setEmail("joao@email.com");
        usuario.setCpf("129.655.344-23");
        usuario.setDataNascimento(LocalDate.now());
        usuario.setDataCriacao(LocalDateTime.now());

        return usuario;
    }

}
