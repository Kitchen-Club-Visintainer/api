package br.com.kitchen.club.controller;

import br.com.kitchen.club.config.security.TokenService;
import br.com.kitchen.club.controller.form.LoginForm;
import br.com.kitchen.club.controller.dto.TokenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginForm form) {
        UsernamePasswordAuthenticationToken dadosLogin = form.converter();

        try {
            var authentication = authManager.authenticate(dadosLogin);
            String token = tokenService.gerarToken(authentication);
//            ELE ENVIA UM FORM COM AS INFORMAÇÕES DO TOKEN E O TIPO DE AUTENTICAÇÃO QUE A API DEVERÁ FAZER FUTURAMENTE
            return ResponseEntity.ok(new TokenDto(token, "Bearer"));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }
//
}
