package br.com.kitchen.club.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Receita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "receita", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ItensReceita> itensReceitas;

    @ManyToMany(mappedBy = "receita")
    private List<LivroReceita> livroReceita;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<LivroReceita> getLivroReceita() {
        return livroReceita;
    }

    public void setLivroReceita(List<LivroReceita> livroReceita) {
        this.livroReceita = livroReceita;
    }
}
