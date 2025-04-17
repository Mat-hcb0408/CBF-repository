package org.cbf.cbf.model.entity;

public class Federacao {

    private final Integer idFederacao;
    private final String nome_federacao;
    private final String sigla_federacao;
    private final String estado_federacao;

    //constructor
    public Federacao(Integer idFederacao, String nome_federacao, String sigla_federacao, String estado_federacao) {
        this.idFederacao = idFederacao;
        this.nome_federacao = nome_federacao;
        this.sigla_federacao = sigla_federacao;
        this.estado_federacao = estado_federacao;
    }

    //getters
    public Integer getIdFederacao() {
        return idFederacao;
    }

    public String getNome_federacao() {
        return nome_federacao;
    }

    public String getSigla_federacao() {
        return sigla_federacao;
    }

    public String getEstado_federacao() {
        return estado_federacao;
    }
}