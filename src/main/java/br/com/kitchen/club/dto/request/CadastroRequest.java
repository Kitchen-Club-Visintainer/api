package br.com.kitchen.club.dto.request;

import br.com.kitchen.club.config.validations.uf.UfValid;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record CadastroRequest(
        @NotBlank(message = "INFORME O NOME COMPLETO DO USUÁRIO")
        String nomeCompleto,

        @NotBlank(message = "INFORME O USUÁRIO QUE SERÁ UTILIZADO NO LOGGIN")
        String username,

        @NotBlank(message = "INFORME O EMAIL DO USUÁRIO")
        String email,

        @NotNull(message = "SENHA NÃO PODE SER NULA")
        @Size(min = 6, max = 15, message = "A SENHA DEVE TER ENTRE 6 E 15 CARACTERES")
        String senha,

        @NotNull(message = "SENHA NÃO PODE SER NULA")
        @Size(min = 6, max = 15, message = "A SENHA DEVE TER ENTRE 6 E 15 CARACTERES")
        String confSenha,
        String cep,
        String logradouro,
        String complemento,
        Integer numero,
        @UfValid(message = "UF INVÁLIDA -> Informar o nome do estado")
        String uf
) {
}
