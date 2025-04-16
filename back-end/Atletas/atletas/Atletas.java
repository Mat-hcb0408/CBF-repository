package com.cbf1.cbf1.atletas;

public class Atletas {
    private final Integer id_atletas;
    private final String nome_atletas;
    private final String nascimento_atletas;
    private final String apelido_atletas;
    private final Integer num_atletas;

    public Atletas(Integer id_atletas, String nome_atletas, String nascimento_atletas, String apelido_atletas, Integer num_atletas) {
        this.id_atletas = id_atletas;
        this.nome_atletas = nome_atletas;
        this.nascimento_atletas = nascimento_atletas;
        this.apelido_atletas = apelido_atletas;
        this.num_atletas = num_atletas;
    }

    public Integer getId_atletas() {
        return id_atletas;
    }

    public String getNome_atletas() {
        return nome_atletas;
    }

    public String getNascimento_atletas() {
        return nascimento_atletas;
    }

    public Integer getNum_atletas() {
        return num_atletas;
    }

    public String getApelido_atletas() {
        return apelido_atletas;
    }
}
