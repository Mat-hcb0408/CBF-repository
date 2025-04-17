package org.cbf.cbf.POJO;

import java.sql.Date;

public class Despesas {
    private int idDespesa;
    private Date dataDespesas;
    private float valorDespesas;
    private String destinatarioDespesas;
    private String remetenteDespesas;
    private String descricaoDespesas;

    public Despesas(int idDespesa, Date dataDespesas, float valorDespesas, String destinatarioDespesas, String remetenteDespesas, String descricaoDespesas) {
        this.idDespesa = idDespesa;
        this.dataDespesas = dataDespesas;
        this.valorDespesas = valorDespesas;
        this.destinatarioDespesas = destinatarioDespesas;
        this.remetenteDespesas = remetenteDespesas;
        this.descricaoDespesas = descricaoDespesas;
    }

    public int getIdDespesa() {
        return idDespesa;
    }

    public void setIdDespesa(int idDespesa) {
        this.idDespesa = idDespesa;
    }

    public Date getDataDespesas() {
        return dataDespesas;
    }

    public void setDataDespesas(Date dataDespesas) {
        this.dataDespesas = dataDespesas;
    }

    public float getValorDespesas() {
        return valorDespesas;
    }

    public void setValorDespesas(float valorDespesas) {
        this.valorDespesas = valorDespesas;
    }

    public String getDestinatarioDespesas() {
        return destinatarioDespesas;
    }

    public void setDestinatarioDespesas(String destinatarioDespesas) {
        this.destinatarioDespesas = destinatarioDespesas;
    }

    public String getRemetenteDespesas() {
        return remetenteDespesas;
    }

    public void setRemetenteDespesas(String remetenteDespesas) {
        this.remetenteDespesas = remetenteDespesas;
    }

    public String getDescricaoDespesas() {
        return descricaoDespesas;
    }

    public void setDescricaoDespesas(String descricaoDespesas) {
        this.descricaoDespesas = descricaoDespesas;
    }
}
