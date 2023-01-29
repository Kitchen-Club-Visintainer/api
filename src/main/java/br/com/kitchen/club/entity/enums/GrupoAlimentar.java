package br.com.kitchenclub.entity.enums;

public enum GrupoAlimentar {

    PROTEINAS("Proteinas"),
    CARBOIDRATOS("Carboidratos"),
    GORDURAS("Gorduras"),
    VITAMINAS_MINERAIS("Vitaminas e minerais");

    private String descricao;
    GrupoAlimentar(String descricao) {
        this.descricao = descricao;
    }
}
