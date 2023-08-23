package br.com.kitchen.club.entity;

import br.com.kitchen.club.entity.enums.GrupoAlimentar;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Ingredientes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String valorNutricional;

    @Enumerated(EnumType.STRING)
    private GrupoAlimentar grupoAlimentar;

    @OneToMany(mappedBy = "ingredientes", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Receita> receita;

    public List<Receita> getReceita() {
        return receita;
    }

    public void setReceita(List<Receita> receita) {
        this.receita = receita;
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
}
