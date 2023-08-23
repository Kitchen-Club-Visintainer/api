package br.com.kitchen.club.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class LivroReceita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(name = "receita",
            joinColumns = @JoinColumn(name = "receita_id"),
            inverseJoinColumns = @JoinColumn(name = "livro_receita_id"))
    private List<Receita> receita;

    private Boolean publica;

    private Integer compartilhada;

    private Integer likes;

    private Float rendimento;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getPublica() {
        return publica;
    }

    public void setPublica(Boolean publica) {
        this.publica = publica;
    }

    public Integer getCompartilhada() {
        return compartilhada;
    }

    public void setCompartilhada(Integer compartilhada) {
        this.compartilhada = compartilhada;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Float getRendimento() {
        return rendimento;
    }

    public void setRendimento(Float rendimento) {
        this.rendimento = rendimento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Receita> getReceita() {
        return receita;
    }

    public void setReceita(List<Receita> receita) {
        this.receita = receita;
    }
}
