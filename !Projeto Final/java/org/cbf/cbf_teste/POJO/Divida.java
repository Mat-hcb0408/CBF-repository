package org.cbf.cbf_teste.POJO;

public class Divida {
    private Integer id;
    private Double valor;
    private String validade;
    private Double juros;
    private String credor;
    private String fiador;
    private String tipo;
    private String descricao;

    // Construtor, getters e setters
    public Divida(Integer id, Double valor, String validade, Double juros, String credor,String fiador, String tipo, String descricao) {
        this.id = id;
        this.valor = valor;
        this.validade = validade;
        this.juros = juros;
        this.credor = credor;
        this.fiador = fiador;
        this.tipo = tipo;
        this.descricao = descricao;
    }

    // Getters
    public Integer getId() { return id; }
    public Double getValor() { return valor; }
    public String getValidade() { return validade; }
    public Double getJuros() { return juros; }
    public String getCredor() { return credor; }
    public String getFiador() {return fiador; }
    public String getTipo() { return tipo; }
    public String getDescricao() { return descricao; }
}