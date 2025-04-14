package com.cbf1.cbf1.receitas;

public class Receitas {
    private final Integer idReceita;
    private final String dataReceita;
    private final Double valorReceita;
    private final Double fonteReceita;
    private final Double irpj;
    private final String descricaoReceita;

    //constructor
    public Receitas(Integer idReceita, String dataReceita, Double valorReceita, Double fonteReceita, Double irpj, String descricaoReceita) {
        this.idReceita = idReceita;
        this.dataReceita = dataReceita;
        this.valorReceita = valorReceita;
        this.fonteReceita = fonteReceita;
        this.irpj = irpj;
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

    public Double getFonteReceita() {
        return fonteReceita;
    }

    public String getDescricaoReceita() {
        return descricaoReceita;
    }

    public Double getIrpj() {
        return irpj;
    }
}
