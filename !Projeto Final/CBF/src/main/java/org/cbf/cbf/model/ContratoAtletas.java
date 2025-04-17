package org.cbf.cbf.model;

public class ContratoAtletas {
    private final Integer id_contrato;
    private final Integer id_atleta;
    private final Integer id_clube;
    private final String data_inicio;
    private final String data_termino;
    private final String tipo_contrato;
    private final Boolean status_contrato;

    public ContratoAtletas(Integer id_contrato, String data_inicio, String data_termino, String tipo_contrato, Boolean status_contrato, Integer id_atleta, Integer id_clube) {
        this.id_contrato = id_contrato;
        this.data_inicio = data_inicio;
        this.data_termino = data_termino;
        this.tipo_contrato = tipo_contrato;
        this.status_contrato = status_contrato;
        this.id_atleta = id_atleta;
        this.id_clube = id_clube;
    }

    public Integer getId_contrato() {
        return id_contrato;
    }

    public String getData_inicio() {
        return data_inicio;
    }

    public String getData_termino() {
        return data_termino;
    }

    public String getTipo_contrato() {
        return tipo_contrato;
    }

    public Boolean getStatus_contrato() {
        return status_contrato;
    }

    public Integer getId_atleta() {
        return id_atleta;
    }

    public Integer getId_clube() {
        return id_clube;
    }
}
