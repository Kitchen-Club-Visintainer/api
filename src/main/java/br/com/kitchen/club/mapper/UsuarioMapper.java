package br.com.kitchen.club.mapper;

import br.com.kitchen.club.dto.request.CadastroRequest;
import br.com.kitchen.club.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public Usuario dtoToEntity(CadastroRequest cadastro) {
        var usuario = new Usuario(
                cadastro.nomeCompleto(),
                cadastro.username(),
                cadastro.email()
        );

        return usuario;
    }

}
