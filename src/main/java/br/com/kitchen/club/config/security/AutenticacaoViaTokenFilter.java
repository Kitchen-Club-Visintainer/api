package br.com.kitchen.club.config.security;

import br.com.kitchen.club.entity.Usuario;
import br.com.kitchen.club.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

    private static final String AUTH_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    private UsuarioRepository usuarioRepository;

    private TokenService tokenService;

    public AutenticacaoViaTokenFilter(UsuarioRepository usuarioRepository, TokenService tokenService) {
        this.usuarioRepository = usuarioRepository;
        this.tokenService = tokenService;
    }

    private String recuperarToken(HttpServletRequest request) {
        String token = request.getHeader(AUTH_HEADER);

        if (Strings.isBlank(token) || !token.startsWith(BEARER_PREFIX)) {
            return null;
        } else {
            return token.substring(7);
        }
    }

    private void autenticarCliente(String token) {
        String username = tokenService.buscarSubject(token);
        Usuario usuario = usuarioRepository.findByUsuario(username)
                .orElseThrow(() -> new SecurityException("USUÁRIO NÃO ENCONTRADO"));
        var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = recuperarToken(request);
        if (Objects.nonNull(token))
            autenticarCliente(token);

        filterChain.doFilter(request, response);
    }
}
