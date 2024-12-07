package br.com.safeMind.api.comon.infra.security.config;

import br.com.safeMind.api.comon.infra.security.service.TokenService;
import br.com.safeMind.api.usuario.model.Usuario;
import br.com.safeMind.api.usuario.service.UsuarioService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;

    @Autowired
    UsuarioService usuarioService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recuperarToken(request);
        var login = tokenService.validarToken(token);

        if(login != null){
            Usuario usuario = usuarioService.pesquisarPorUsuario(login);
            var permissoes = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
            var autenticacao = new UsernamePasswordAuthenticationToken(usuario,null,permissoes);
            SecurityContextHolder.getContext().setAuthentication(autenticacao);
        }

        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        var cabecalhoAutorizacao = request.getHeader("Authorization");
        if(cabecalhoAutorizacao == null) return null;
        return cabecalhoAutorizacao.replace("Bearer", "");
    }
}
