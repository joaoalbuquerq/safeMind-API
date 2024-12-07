package br.com.safeMind.api.comon.infra.security.service;

import br.com.safeMind.api.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

@Component
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    UsuarioService usuarioService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var usuario = usuarioService.pesquisarPorUsuario(username);
        return new User(usuario.getLogin(), usuario.getSenha(), new ArrayList<>());
    }
}
