package br.com.kitchen.club.dto.request;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginDTO {

    private String nome;
    private String senha;

    public String getNome() {
        return nome;
    }

    //@NotEmpty(message = "Senha não pode ser vazia.")
    public String getSenha() {
        return senha;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(nome, senha);
    }

}
