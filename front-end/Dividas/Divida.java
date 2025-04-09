package org.example.projeto;

import javafx.beans.property.*;

public class Divida {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final DoubleProperty valor = new SimpleDoubleProperty();
    private final StringProperty validade = new SimpleStringProperty();
    private final DoubleProperty juros = new SimpleDoubleProperty();
    private final StringProperty credor = new SimpleStringProperty();
    private final StringProperty tipo = new SimpleStringProperty();
    private final StringProperty descricao = new SimpleStringProperty();

    public Divida(int id, double valor, String validade, double juros, String credor, String tipo, String descricao) {
        this.id.set(id);
        this.valor.set(valor);
        this.validade.set(validade);
        this.juros.set(juros);
        this.credor.set(credor);
        this.tipo.set(tipo);
        this.descricao.set(descricao);
    }

    public int getId() { return id.get(); }
    public void setId(int value) { id.set(value); }
    public IntegerProperty idProperty() { return id; }

    public double getValor() { return valor.get(); }
    public void setValor(double value) { valor.set(value); }
    public DoubleProperty valorProperty() { return valor; }

    public String getValidade() { return validade.get(); }
    public void setValidade(String value) { validade.set(value); }
    public StringProperty validadeProperty() { return validade; }

    public double getJuros() { return juros.get(); }
    public void setJuros(double value) { juros.set(value); }
    public DoubleProperty jurosProperty() { return juros; }

    public String getCredor() { return credor.get(); }
    public void setCredor(String value) { credor.set(value); }
    public StringProperty credorProperty() { return credor; }

    public String getTipo() { return tipo.get(); }
    public void setTipo(String value) { tipo.set(value); }
    public StringProperty tipoProperty() { return tipo; }

    public String getDescricao() { return descricao.get(); }
    public void setDescricao(String value) { descricao.set(value); }
    public StringProperty descricaoProperty() { return descricao; }
}
