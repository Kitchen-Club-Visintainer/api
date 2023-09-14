package br.com.kitchen.club.entity.enums;

public enum MedidasCulinarias {

    XICARA("1 Xícara", (double) (250/15), 250),
    COLHER_SOPA("1 Colher de sopa", 1.0, 15),
    COLHER_CHA("1 Colher de chá", (double) (5/15), 5),
    COLHER_CAFE("1 Colher de café", (double) (2/15), 2);

    MedidasCulinarias(String descricao, Double equivalenteColherSopa, Integer valorMl) {
        this.descricao = descricao;
        this.equivalenteColherSopa = equivalenteColherSopa;
        this.valorMl = valorMl;
    }

    private String descricao;
    private Double equivalenteColherSopa;
    private Integer valorMl;
}
