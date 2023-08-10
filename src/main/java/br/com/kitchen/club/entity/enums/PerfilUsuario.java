package br.com.kitchen.club.entity.enums;

public enum PerfilUsuario {

    ADMINISTRADOR ("Adminstrados do sistema", 00),
    USUARIO ("Usuários que irão utilizar a plataforma", 01),
    COZINHEIRO ("Usuários que irão criar conteúdos e receitas para a plataforma", 02);

    PerfilUsuario(String descricao, Integer codigo) {
        this.descricao = descricao;
        this.codigo = codigo;
    }

    private String descricao;
    private Integer codigo;

    public String getDescricao() {
        return descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }
}
