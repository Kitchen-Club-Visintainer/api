package br.com.kitchen.club.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "receita")
public class Receita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_receita")
    private String nomeReceita;

    @OneToMany(mappedBy = "receita", fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<ItensReceita> itensReceitas;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<LivroReceita> livroReceita;

    public Receita() {
    }

    public Receita(String nomeReceita, List<LivroReceita> livroReceita) {
        this.nomeReceita = nomeReceita;
        this.livroReceita = livroReceita;
    }

    public Receita(String nomeReceita, List<ItensReceita> itensReceitas, List<LivroReceita> livroReceita) {
        this.nomeReceita = nomeReceita;
        this.itensReceitas = itensReceitas;
        this.livroReceita = livroReceita;
    }

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

    public List<ItensReceita> getItensReceitas() {
        return itensReceitas;
    }

    public void setItensReceitas(List<ItensReceita> itensReceitas) {
        this.itensReceitas = itensReceitas;
    }

    public String getNomeReceita() {
        return nomeReceita;
    }

    public void setNomeReceita(String nomeReceita) {
        this.nomeReceita = nomeReceita;
    }

    @Override
    public String toString() {
        return "Receita{" +
                "id=" + id +
                ", nomeReceita='" + nomeReceita + '\'' +
                ", itensReceitas=" + itensReceitas +
                ", livroReceita=" + livroReceita +
                '}';
    }
}
