package br.com.kitchen.club.entity.enums;

import br.com.kitchen.club.config.exception.ParametroException;

import java.util.Arrays;

public enum GrupoAlimentar {

    PROTEINAS("Carnes e ovos", 1),
    CARBOIDRATOS("Cereais, pães e tubérculos", 2),
    GORDURAS("Óleos e gorduras", 3),
    VEGETAIS("Hortaliças", 4),
    LEGUMINOSAS("Grãos como feijão e soja", 5),
    FRUTAS("Frutas", 6),
    LATICINIOS("Leite e derivados", 7),
    DOCES("Doces e fontes de açúcar", 8),
    OUTROS("Grupo alimentar desconhecido", 99);

    private String descricao;
    private Integer codigo;

    GrupoAlimentar(String descricao, Integer codigo) {
        this.descricao = descricao;
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    /**
     * Informa o grupo alimentar correspondete ao código.
     *
     * @param cod cadastrado no Grupo Alimentar
     * @return Grupo Alimentar
     * @throws ParametroException caso o código informado não esteja cadastrado
     */
    public static GrupoAlimentar fromCodigo(Integer cod) throws ParametroException {
        return Arrays.stream(GrupoAlimentar.values())
                .filter(grupo -> grupo.codigo.equals(cod))
                .findFirst()
                .orElseThrow(() -> new ParametroException(String.valueOf(cod)));
    }
}
