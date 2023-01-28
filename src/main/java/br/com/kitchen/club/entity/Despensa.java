package br.com.kitchen.club.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//@Entity
public class Despensa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Ingredientes ingredientes;

    private Float quantidade;

    private DateTimeFormat dataValidade;

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

    public DateTimeFormat getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(DateTimeFormat dataValidade) {
        this.dataValidade = dataValidade;
    }
}
