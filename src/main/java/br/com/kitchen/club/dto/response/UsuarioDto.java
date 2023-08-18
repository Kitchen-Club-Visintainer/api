package br.com.kitchen.club.dto.response;

import br.com.kitchen.club.entity.Enderecos;
import br.com.kitchen.club.entity.Usuario;

import java.util.Objects;

public record UsuarioDto(
        Long id,
        String nomeCompleto,
        String usuario,
        String email,
        Boolean ativo,
        String perfilUsuario,
        Enderecos enderecos) {

    public static UsuarioDto of(Usuario usuario) {
        return new UsuarioDto(
                usuario.getId(),
                usuario.getNomeCompleto(),
                usuario.getUsuario(),
                usuario.getEmail(),
                usuario.getAtivo(),
                Objects.nonNull(usuario.getPerfilUsuario()) ? usuario.getPerfilUsuario().name() : null,
                usuario.getEndereco());
    }
}
