package br.com.kitchen.club.entity.enums;

/**
 * @author Ricardo Giaviti
 * @version 1.1.1
 * @since 1.0.0
 * @see `https://gist.github.com/rgiaviti/510c260164ea25ef0449209f26560c3d`
 */

public enum Uf {
    AM("Amazonas", "AM", "Manaus"),
    AL("Alagoas", "AL", "Maceió"),
    AC("Acre", "AC", "Rio Branco"),
    AP("Amapá", "AP", "Macapá"),
    BA("Bahia", "BA", "Salvador"),
    PA("Pará", "PA", "Belém"),
    MT("Mato Grosso", "MT", "Cuiabá"),
    MG("Minas Gerais", "MG", "Belo Horizonte"),
    MS("Mato Grosso do Sul", "MS", "Campo Grande"),
    GO("Goiás", "GO", "Goiânia"),
    MA("Maranhão", "MA", "São Luís"),
    RS("Rio Grande do Sul", "RS", "Porto Alegre"),
    TO("Tocantins", "TO", "Palmas"),
    PI("Piauí", "PI", "Teresina"),
    SP("São Paulo", "SP", "São Paulo"),
    RO("Rondônia", "RO", "Porto Velho"),
    RR("Roraima", "RR", "Boa Vista"),
    PR("Paraná", "PR", "Curitiba"),
    CE("Ceará", "CE", "Fortaleza"),
    PE("Pernambuco", "PE", "Recife"),
    SC("Santa Catarina", "SC", "Florianópolis"),
    PB("Paraíba", "PB", "João Pessoa"),
    RN("Rio Grande do Norte", "RN", "Natal"),
    ES("Espírito Santo", "ES", "Vitória"),
    RJ("Rio de Janeiro", "RJ", "Rio de Janeiro"),
    SE("Sergipe", "SE", "Aracaju"),
    DF("Distrito Federal", "DF", "Brasília");

    private final String nome;
    private final String sigla;
    private final String capital;

    /**
     * Construtor do enum
     *
     * @param nome    nome da unidade da federação completo
     * @param sigla   sigla da unidade da federação
     * @param capital nome da capital da unidade da federação
     */
    Uf(final String nome, final String sigla, final String capital) {
        this.nome = nome;
        this.sigla = sigla;
        this.capital = capital;
    }

    /**
     * Converte a partir do nome da Unidade da Federacao, para o respectivo enum.
     *
     * @param nomeUf o nome da Unidade da Federação. Exemplo: "São Paulo"
     * @return o enum da Unidade da Federação
     * @throws IllegalArgumentException caso não ache o enum pelo nome da UF
     */
    public static Uf fromUF(final String nomeUf) {
        for (final Uf uf : Uf.values()) {
            if (uf.nome.equalsIgnoreCase(nomeUf)) {
                return uf;
            }
        }

        throw new IllegalArgumentException(nomeUf);
    }

    /**
     * Converte a partir da Sigla da UF no parâmetro, para o enum da Unidade da Federação.
     *
     * @param sigla da Unidade da Federação. Exemplo: "MG"
     * @return a Unidade da Federação
     * @throws IllegalArgumentException caso a sigla da UF não exista
     */
    public static Uf fromSigla(final String sigla) {
        for (final Uf uf : Uf.values()) {
            if (uf.sigla.equalsIgnoreCase(sigla)) {
                return uf;
            }
        }

        throw new IllegalArgumentException(sigla);
    }

    /**
     * Converte, a partir do nome da capital da UF, para o Enum.
     *
     * @param capital da Unidade da Federação. Exemplo: "Porto Alegre"
     * @return a Unidade da Federacao com a capital passada no parâmetro
     * @throws IllegalArgumentException caso o nome da capital não exista
     */
    public static Uf fromCapital(final String capital) {
        for (final Uf uf : Uf.values()) {
            if (uf.capital.equalsIgnoreCase(capital)) {
                return uf;
            }
        }

        throw new IllegalArgumentException(capital);
    }

    /**
     * Obtém a sigla da UF
     *
     * @return a sigla da UF
     */
    public String sigla() {
        return this.sigla;
    }

    /**
     * Nome completo da UF
     *
     * @return nome completo da UF
     */
    public String nome() {
        return this.nome;
    }

    /**
     * Nome da capital da UF
     *
     * @return nome da capital da UF
     */
    public String capital() {
        return this.capital;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UnidadeFederacao{");
        sb.append("nome='").append(nome).append('\'');
        sb.append(", sigla='").append(sigla).append('\'');
        sb.append(", capital='").append(capital).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
