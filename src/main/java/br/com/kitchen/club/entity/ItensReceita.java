package br.com.kitchen.club.entity;

import br.com.kitchen.club.entity.enums.MedidasCulinarias;
import jakarta.persistence.*;

@Entity
public class ItensReceita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Float quantidade;

    @Enumerated(EnumType.STRING)
    private MedidasCulinarias unidadeMedida;

    @JoinColumn()
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Ingredientes ingredientes;

    @JoinColumn()
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
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
