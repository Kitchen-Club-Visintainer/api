package br.com.kitchen.club.controller;

import br.com.kitchen.club.config.security.TokenService;
import br.com.kitchen.club.dto.TokenDto;
import br.com.kitchen.club.dto.request.LoginDTO;
import br.com.kitchen.club.dto.response.Response;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    private static final Logger log = LoggerFactory.getLogger(AuthController.class); //TODO:revisar

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
    public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginDTO form) {
        var dadosLogin = form.converter();
        try {
            var authentication = authManager.authenticate(dadosLogin);
            String token = tokenService.gerarToken(authentication);
//            ELE ENVIA UM FORM COM AS INFORMAÇÕES DO TOKEN E O TIPO DE AUTENTICAÇÃO QUE A API DEVERÁ FAZER FUTURAMENTE
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
    public ResponseEntity<Response<TokenDto>> gerarTokenJwt(@Valid @RequestBody LoginDTO authenticationDto,
                                                            BindingResult result) throws AuthenticationException {
        Response<TokenDto> response = new Response<>();

        if (result.hasErrors()) {
            log.error("Erro validando lançamento: {}", result.getAllErrors());
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        log.info("Gerando token para o Usuario {}.", authenticationDto.getNome());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationDto.getNome(), authenticationDto.getSenha()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenService.gerarToken(authentication);
        response.setData(new TokenDto(token, "Bearer"));

        return ResponseEntity.ok(response);
    }

}
