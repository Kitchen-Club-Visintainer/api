package br.com.kitchen.club.controller;

import br.com.kitchen.club.config.exception.ParametroException;
import br.com.kitchen.club.config.exception.ServiceException;
import br.com.kitchen.club.dto.request.CadastroRequest;
import br.com.kitchen.club.dto.request.UsuarioShallowDto;
import br.com.kitchen.club.dto.response.UsuarioDto;
import br.com.kitchen.club.entity.Usuario;
import br.com.kitchen.club.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    //TODO: ver a parte de permissões

    /**
     * Recebe um formulário de cadastro para um novo usuário
     *
     * @param cadastro
     * @return ResponseEntity<Response < TokenDto>>
     * @throws AuthenticationException
     */
    @PostMapping(value = "/novoUsuario")
    public ResponseEntity<String> cadastrarNovoUsuario(@Valid @RequestBody CadastroRequest cadastro) throws ParametroException {
        usuarioService.cadastrarNovoUsuario(cadastro);
        return new ResponseEntity<>("Recebido", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> buscarTodosUsuarios() {
        var usuarios = usuarioService.buscarTodosUsuarios();
        var dto = usuarios.stream()
                .map(UsuarioDto::of)
                .toList();
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> buscarUsuarioPeloId(@PathVariable Long id) throws ParametroException {
        var usuario = usuarioService.buscarUsuario(id);
        var dto = UsuarioDto.of(usuario);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Usuario> atualizarUsuario(@Valid @RequestBody UsuarioShallowDto atualizacao) throws ParametroException, ServiceException {
        var usuario = usuarioService.atualizarUsuario(atualizacao);
        if (usuario.isPresent())
            return new ResponseEntity<>(usuario.get(), HttpStatus.OK);
        throw new ServiceException("Erro na atualização do usuario");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarUsuario(@PathVariable Long id) throws ParametroException {
        var usuario = usuarioService.buscarUsuario(id);
        var username = usuario.getUsuario();
        usuarioService.delete(usuario);
        var mensagem = "Usuário " + username + " foi excluído com sucesso";
        return new ResponseEntity<>(mensagem, HttpStatus.OK);
    }
}
