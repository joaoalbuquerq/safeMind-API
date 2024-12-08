package br.com.safeMind.api.comon.infra.security.controller;

import br.com.safeMind.api.comon.infra.security.dto.LoginRequestDTO;
import br.com.safeMind.api.comon.infra.security.dto.LoginResponseDTO;
import br.com.safeMind.api.comon.infra.security.service.TokenService;
import br.com.safeMind.api.usuario.dto.UsuarioCadastroDTO;
import br.com.safeMind.api.usuario.model.Usuario;
import br.com.safeMind.api.usuario.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AutenticacaoController {

    private final UsuarioService usuarioService;
    private final PasswordEncoder encoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO dto){
        var usuario = usuarioService.pesquisarPorUsuario(dto.username());

        if(encoder.matches(dto.senha(), usuario.getSenha())){
            String token = tokenService.gerarTokenAutenticacao(usuario);
            return ResponseEntity.ok(new LoginResponseDTO(usuario.getLogin(), token));
        }
        return ResponseEntity.badRequest().body("Senha incorreta");
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody @Valid UsuarioCadastroDTO dto){


            var user = usuarioService.cadastrar(dto);
            String token = tokenService.gerarTokenAutenticacao(user);
            return ResponseEntity.ok(new LoginResponseDTO(user.getLogin(), token));



    }
}
