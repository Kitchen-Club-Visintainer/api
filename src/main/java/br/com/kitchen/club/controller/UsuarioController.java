package br.com.kitchen.club.controller;

import br.com.kitchen.club.config.exception.ParametroException;
import br.com.kitchen.club.dto.request.CadastroRequest;
import br.com.kitchen.club.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    /**
     * Recebe um formulário de cadastro para um novo usuário
     *
     * @param cadastro
     * @return ResponseEntity<Response<TokenDto>>
     * @throws AuthenticationException
     * */
    @PostMapping(value = "/novoUsuario")
    public ResponseEntity<String> cadastrarNovoUsuario(@RequestBody CadastroRequest cadastro) throws ParametroException {

        usuarioService.cadastrarNovoUsuario(cadastro);

        return new ResponseEntity<>("Recebido", HttpStatus.OK);
    }
}
