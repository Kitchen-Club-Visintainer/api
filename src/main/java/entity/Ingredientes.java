package entity;

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

}
