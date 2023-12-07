package br.com.kitchen.club.controller;

import br.com.kitchen.club.config.security.TokenService;
import br.com.kitchen.club.dto.auth.LoginDto;
import br.com.kitchen.club.dto.auth.Response;
import br.com.kitchen.club.dto.auth.TokenDto;
import jakarta.validation.Valid;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final Log log = LogFactory.getLog(this.getClass());

    public AuthController(AuthenticationManager authManager,
                          TokenService tokenService,
                          AuthenticationManager authenticationManager,
                          UserDetailsService userDetailsService) {
        this.authManager = authManager;
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping(value = "/api")
    public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginDto form) {
        var dadosLogin = form.converter();
        try {
            var authentication = authManager.authenticate(dadosLogin);
            var token = tokenService.gerarToken(authentication);

            return ResponseEntity.ok(new TokenDto(token, "Bearer"));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Gera e retorna um novo token JWT para autenticação via Angular.
     *
     * @param authenticationDto
     * @param result
     * @return ResponseEntity<Response<TokenDto>>
     * @throws AuthenticationException
     */
    @PostMapping
    public ResponseEntity<Response<TokenDto>> gerarTokenJwt(@Valid @RequestBody LoginDto authenticationDto,
                                                            BindingResult result) throws AuthenticationException {
        Response<TokenDto> response = new Response<>();

        if (result.hasErrors()) {
            log.error(String.format("Erro validando lançamento: %s", result.getAllErrors()));
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        log.info(String.format("Gerando token para o Usuario %s.", authenticationDto.getNome()));
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationDto.getNome(), authenticationDto.getSenha()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        var token = tokenService.gerarToken(authentication);
        response.setData(new TokenDto(token, "Bearer"));

        return ResponseEntity.ok(response);
    }

}
