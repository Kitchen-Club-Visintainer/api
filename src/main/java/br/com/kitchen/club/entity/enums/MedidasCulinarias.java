package br.com.kitchen.club.entity.enums;

public enum MedidasCulinarias {

    XICARA("1 Xícara", (double) (16), 240),
    COLHER_SOPA("1 Colher de sopa", 1.0, 15),
    COLHER_CHA("1 Colher de chá", (double) (1/3), 5),
    COLHER_CAFE("1 Colher de café", (double) (2/15), 2),
    UNIDADE("1 Unidade", 1.0, 1),
    GRAMA("1 Grama", (double) (16/240), 1);

    MedidasCulinarias(String descricao, Double equivalenteColherSopa, Integer valorMl) {
        this.descricao = descricao;
        this.equivalenteColherSopa = equivalenteColherSopa;
        this.valorMl = valorMl;
    }

    private String descricao;
    private Double equivalenteColherSopa;
    private Integer valorMl;

    public String getDescricao() {
        return descricao;
    }

    public Double getEquivalenteColherSopa() {
        return equivalenteColherSopa;
    }

    public Integer getValorMl() {
        return valorMl;
    }
}
