package br.com.safeMind.api.comon.infra.security.service;

import br.com.safeMind.api.usuario.model.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarTokenAutenticacao(Usuario usuario){
        try{
            Algorithm alg = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("login-safemind-api")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(this.gerarTempoExpiracaoToken())
                    .sign(alg);
            return token;
        }catch (JWTCreationException ex){
            throw new RuntimeException("Erro enquanto estava autenticando o usuario");
        }
    }

    public String validarToken(String token){
        try{
            Algorithm alg = Algorithm.HMAC256(secret);

            return JWT.require(alg)
                    .withIssuer("login-safemind-api")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException ex){
            return null;
        }
    }

    private Instant gerarTempoExpiracaoToken() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
