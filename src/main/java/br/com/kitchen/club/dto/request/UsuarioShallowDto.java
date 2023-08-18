package br.com.kitchen.club.dto.request;

import br.com.kitchen.club.entity.Usuario;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public record UsuarioShallowDto(
        String nomeCompleto,
        @NotBlank
        String usuario,
        String email,
        Boolean ativo,
        String perfilUsuario) {

    public static UsuarioShallowDto of(Usuario usuario) {
        return new UsuarioShallowDto(
                usuario.getNomeCompleto(),
                usuario.getUsuario(),
                usuario.getEmail(),
                usuario.getAtivo(),
                Objects.nonNull(usuario.getPerfilUsuario()) ? usuario.getPerfilUsuario().name() : null);
    }
}
