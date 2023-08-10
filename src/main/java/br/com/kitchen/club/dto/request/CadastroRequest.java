package br.com.kitchen.club.dto.request;

public record CadastroRequest(
        String nomeCompleto,
        String username,
        String email,
        String senha,
        String confSenha,
        String cep,
        String logradouro,
        String complemento,
        Integer numero,
        String uf
) {
}
