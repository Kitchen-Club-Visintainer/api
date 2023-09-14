package br.com.kitchen.club.entity;

import br.com.kitchen.club.entity.enums.MedidasCulinarias;
import jakarta.persistence.*;

@Entity
public class ItensReceita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Float quantidade;

    private MedidasCulinarias unidadeMedida;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ingredientes_id")
    private Ingredientes ingredientes;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "receita_id")
    private Receita receita;


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

    public MedidasCulinarias getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(MedidasCulinarias unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public Receita getReceita() {
        return receita;
    }

    public void setReceita(Receita receita) {
        this.receita = receita;
    }
}
