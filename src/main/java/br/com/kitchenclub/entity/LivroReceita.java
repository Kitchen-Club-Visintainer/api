package br.com.kitchenclub.entity;

import javax.persistence.*;

@Entity
public class LivroReceita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    private Receita receita;

    private Boolean publica;

    private Integer compartilhada;

    private Integer likes;

    private Float rendimento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Receita getReceita() {
        return receita;
    }

    public void setReceita(Receita receita) {
        this.receita = receita;
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
}
