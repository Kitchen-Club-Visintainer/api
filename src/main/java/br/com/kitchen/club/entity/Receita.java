package br.com.kitchen.club.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Receita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ingrediente_id")
    private Ingredientes ingredientes;

    private Float quantidade;

    private String unidadeMedida;

    @ManyToMany(mappedBy = "receita")
    private List<LivroReceita> livroReceita;

    public List<LivroReceita> getLivroReceita() {
        return livroReceita;
    }

    public void setLivroReceita(List<LivroReceita> livroReceita) {
        this.livroReceita = livroReceita;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ingredientes getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(Ingredientes ingredientes) {
        this.ingredientes = ingredientes;
    }

    public Float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Float quantidade) {
        this.quantidade = quantidade;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }
}
