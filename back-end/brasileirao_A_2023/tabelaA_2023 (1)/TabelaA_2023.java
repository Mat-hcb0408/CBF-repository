package com.cbf1.cbf1.tabelaA_2023;

public class TabelaA_2023 {
    protected final Integer id_clube_A23;
    protected final Integer vitorias_A23;
    protected final Integer empates_A23;
    protected final Integer derrotas_A23;
    protected final Integer golsPro_A23;
    protected final Integer golsContra_A23;
    protected final Integer jogos_A23;
    protected final Integer pontos_A23;
    protected final Integer saldoGols_A23;

    public TabelaA_2023(Integer id_clube_A23, Integer vitorias_A23, Integer empates_A23, Integer derrotas_A23, Integer golsPro_A23, Integer golsContra_A23, Integer jogos_A23, Integer pontos_A23, Integer saldoGols_A23) {
        this.id_clube_A23 = id_clube_A23;
        this.vitorias_A23 = vitorias_A23;
        this.empates_A23 = empates_A23;
        this.derrotas_A23 = derrotas_A23;
        this.golsPro_A23 = golsPro_A23;
        this.golsContra_A23 = golsContra_A23;
        this.jogos_A23 = jogos_A23;
        this.pontos_A23 = pontos_A23;
        this.saldoGols_A23 = saldoGols_A23;
    }

    public Integer getId_clube_A23() {
        return id_clube_A23;
    }

    public Integer getVitorias_A23() {
        return vitorias_A23;
    }

    public Integer getEmpates_A23() {
        return empates_A23;
    }

    public Integer getDerrotas_A23() {
        return derrotas_A23;
    }

    public Integer getGolsPro_A23() {
        return golsPro_A23;
    }

    public Integer getGolsContra_A23() {
        return golsContra_A23;
    }

    public Integer getJogos_A23() {
        return jogos_A23;
    }

    public Integer getPontos_A23() {
        return pontos_A23;
    }

    public Integer getSaldoGols_A23() {
        return saldoGols_A23;
    }
}
