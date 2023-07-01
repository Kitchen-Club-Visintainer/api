package br.com.kitchen.club.dto;

public class JwtAuthenticationDTO {

    private String nome;
    private String senha;

    public JwtAuthenticationDTO() {
    }


    //@NotEmpty(message = "Senha não pode ser vazia.")
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "JwtAuthenticationDto [nome=" + nome + ", senha=" + senha + "]";
    }

}
