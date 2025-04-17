package com.cbf1.cbf1.arbitros;

public class Arbitros {
    private final Integer id_arbitros;
    private final String nome_arbitros;
    private final Integer id_federacao;

    public Arbitros(Integer id_arbitros, String nome_arbitros, Integer id_federacao) {
        this.id_arbitros = id_arbitros;
        this.nome_arbitros = nome_arbitros;
        this.id_federacao = id_federacao;
    }

    public Integer getId_arbitros() {
        return id_arbitros;
    }

    public String getNome_arbitros() {
        return nome_arbitros;
    }

    public Integer getId_federacao() {
        return id_federacao;
    }
}
