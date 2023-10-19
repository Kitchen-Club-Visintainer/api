package br.com.kitchen.club.entity;

import br.com.kitchen.club.entity.enums.PerfilUsuario;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;

@Entity
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeCompleto;

    private String usuario;

    private String email;

    private String senha;

    private Boolean ativo;

    private PerfilUsuario perfilUsuario;

    //TODO: Colocar atributo de ATIVO  e PERFIS

    @OneToOne
    private Enderecos endereco;

    public Usuario() {
    }

    /**
     * Construtor de novos usuários
     *
     * @param nomeCompleto  Nome completo do usuário
     * @param usuario       Nome de usuário para login no sistema
     * @param email         Email do usuário
     * @param senha         Senha do usuário - será feita a encriptação da senha
     *
     * @version 1.0.0
     * @auhor Davi Visintainer
     * */
    public Usuario(String nomeCompleto, String usuario, String email, String senha) {
        super();
        this.nomeCompleto = nomeCompleto;
        this.usuario = usuario;
        this.email = email;
        this.senha = new BCryptPasswordEncoder().encode(senha);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = new BCryptPasswordEncoder().encode(senha);
    }

    public Enderecos getEndereco() {
        return endereco;
    }

    public void setEndereco(Enderecos endereco) {
        this.endereco = endereco;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public PerfilUsuario getPerfilUsuario() {
        return perfilUsuario;
    }

    public void setPerfilUsuario(PerfilUsuario perfilUsuario) {
        this.perfilUsuario = perfilUsuario;
    }

    //  MÉTODOS VINDOS DA UserDetails

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return getSenha();
    }

    @Override
    public String getUsername() {
        return getUsuario();
    }

//  DESENVOLVER DEPOIS OS MÉTODOS ABAIXO:

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
