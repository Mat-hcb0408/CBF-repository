package org.cbf.cbf.model;

public class Clubes {
    private final Integer id_clube;
    private final String escudo_clube;
    private final String nome_clube;
    private final Integer fundacao_clube;
    private final String estado_clube;
    private final Integer idFed_clube;


    public Clubes(Integer id_clube, String escudo_clube, String nome_clube, Integer fundacao_clube, String estado_clube, Integer idFed_clube) {
        this.id_clube = id_clube;
        this.escudo_clube = escudo_clube;
        this.nome_clube = nome_clube;
        this.fundacao_clube = fundacao_clube;
        this.estado_clube = estado_clube;
        this.idFed_clube = idFed_clube;

    }

    public Integer getId_clube() {
        return id_clube;
    }

    public String getEscudo_clube() {
        return escudo_clube;
    }

    public String getNome_clube() {
        return nome_clube;
    }

    public Integer getFundacao_clube() {
        return fundacao_clube;
    }

    public String getEstado_clube() {
        return estado_clube;
    }

    public Integer getIdFed_clube() {
        return idFed_clube;
    }
}