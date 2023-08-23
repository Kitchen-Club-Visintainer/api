package br.com.kitchen.club.config.security;

import br.com.kitchen.club.entity.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    protected final Log logger = LogFactory.getLog(this.getClass());

    @Value("${token.jwt.expiration}")
    private String expiration;

    @Value("${token.jwt.secret}")
    private String secret;

    public String gerarToken(Authentication authentication) {

        Usuario logado = (Usuario) authentication.getPrincipal();
        Date hoje = new Date();
        Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));

        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                .withSubject(logado.getUsername())
                .withExpiresAt(dataExpiracao)
                .sign(algorithm);
    }

    public String buscarSubject(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            throw new SecurityException("FALHA NA AUTENTICAÇÃO");
        }
    }
}
