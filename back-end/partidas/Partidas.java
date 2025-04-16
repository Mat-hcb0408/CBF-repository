package com.cbf1.cbf1.partidas;

public class Partidas {
    private final Integer id_partida;
    private final Integer gols_mandante;
    private final Integer gols_visitante;
    private final String data_partida;

    public Partidas(Integer id_partida, Integer gols_mandante, Integer gols_visitante, String data_partida) {
        this.id_partida = id_partida;
        this.gols_mandante = gols_mandante;
        this.gols_visitante = gols_visitante;
        this.data_partida = data_partida;
    }

    public Integer getId_partida() {
        return id_partida;
    }

    public Integer getGols_mandante() {
        return gols_mandante;
    }

    public Integer getGols_visitante() {
        return gols_visitante;
    }

    public String getData_partida() {
        return data_partida;
    }
}
