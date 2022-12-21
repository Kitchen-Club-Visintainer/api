package entity;

import javax.persistence.Entity;

@Entity
public class Usuario {

    private Long id;

    private String nomeCompleto;

    private String userName;

    private String email;

    private String password;

    private Enderecos endereco;

    private LivroReceita livroReceita;

    private Despensa despensa;
}
