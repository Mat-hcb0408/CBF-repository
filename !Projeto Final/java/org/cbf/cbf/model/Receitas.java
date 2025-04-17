package org.cbf.cbf.model;

public class Receitas {
    private final Integer idReceita;
    private final String dataReceita;
    private final Double valorReceita;
    private final String fonteReceita;
    private final Double irpjReceita;
    private final String descricaoReceita;

    //constructor
    public Receitas(Integer idReceita, String dataReceita, Double valorReceita, String fonteReceita, Double irpjReceita, String descricaoReceita) {
        this.idReceita = idReceita;
        this.dataReceita = dataReceita;
        this.valorReceita = valorReceita;
        this.fonteReceita = fonteReceita;
        this.irpjReceita = irpjReceita;
        this.descricaoReceita = descricaoReceita;
    }

    //getters
    public Integer getIdReceita() {
        return idReceita;
    }

    public String getDataReceita() {
        return dataReceita;
    }

    public Double getValorReceita() {
        return valorReceita;
    }

    public String getFonteReceita() {
        return fonteReceita;
    }

    public String getDescricaoReceita() {
        return descricaoReceita;
    }

    public Double getIrpjReceita() {
        return irpjReceita;
    }
}
