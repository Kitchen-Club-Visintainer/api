package br.com.kitchenclub.entity;

import br.com.kitchenclub.entity.enums.GrupoAlimentar;

import javax.persistence.*;

@Entity
public class Ingredientes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String valorNutricional;

    @Enumerated(EnumType.STRING)
    private GrupoAlimentar grupoAlimentar;

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
}
