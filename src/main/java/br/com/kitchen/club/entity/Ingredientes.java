package br.com.kitchen.club.entity;

import br.com.kitchen.club.entity.enums.GrupoAlimentar;
import jakarta.persistence.*;

@Entity
public class Ingredientes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String valorNutricional;

    @Enumerated(EnumType.STRING)
    private GrupoAlimentar grupoAlimentar;

    public Ingredientes() {
    }

    public Ingredientes(String nome, String valorNutricional) {
        this.nome = nome;
        this.valorNutricional = valorNutricional;
    }

    public Ingredientes(String nome, String valorNutricional, GrupoAlimentar grupoAlimentar) {
        this.nome = nome;
        this.valorNutricional = valorNutricional;
        this.grupoAlimentar = grupoAlimentar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getValorNutricional() {
        return valorNutricional;
    }

    public void setValorNutricional(String valorNutricional) {
        this.valorNutricional = valorNutricional;
    }

    public GrupoAlimentar getGrupoAlimentar() {
        return grupoAlimentar;
    }

    public void setGrupoAlimentar(GrupoAlimentar grupoAlimentar) {
        this.grupoAlimentar = grupoAlimentar;
    }

    @Override
    public String toString() {
        return "Ingredientes{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", valorNutricional='" + valorNutricional + '\'' +
                ", grupoAlimentar=" + grupoAlimentar +
                '}';
    }
}
