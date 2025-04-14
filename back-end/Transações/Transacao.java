package org.cbf.cbf_teste;

public class Transacao {
    private Integer idTransacao;
    private final Double dataTransacoes;
    private String valorTransacoes;
    private Double destinatarioTransacoes;
    private String remetenteTransacoes;
    private String descricaoTransacoes;

    // Construtor, getters e setters
    public Transacao(Integer id, Double valor, String validade, Double juros, String credor,String fiador, String tipo, String descricao) {
        this.idTransacao = id;
        this.dataTransacoes = valor;
        this.valorTransacoes = validade;
        this.destinatarioTransacoes = juros;
        this.remetenteTransacoes = credor;
        this.descricaoTransacoes = fiador;

    }

    // Getters
    public Integer getId() { return idTransacao; }
    public Double getValor() { return dataTransacoes; }
    public String getValidade() { return valorTransacoes; }
    public Double getJuros() { return destinatarioTransacoes; }
    public String getCredor() { return remetenteTransacoes; }
    public String getFiador() {return descricaoTransacoes; }

}