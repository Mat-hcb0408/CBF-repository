package org.cbf.cbf.POJO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Transacao {
    private Integer idTransacao;
    private LocalDate dataTransacoes;  // Alterar para LocalDate
    private Double valorTransacoes;
    private String destinatarioTransacoes;
    private String remetenteTransacoes;
    private String descricaoTransacoes;

    public Transacao(Integer idTransacao, String dataTransacoes, Double valorTransacoes,
                     String destinatarioTransacoes, String remetenteTransacoes, String descricaoTransacoes) {

        this.idTransacao = idTransacao;
        // Converter String para LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.dataTransacoes = LocalDate.parse(dataTransacoes, formatter);
        this.valorTransacoes = valorTransacoes;
        this.destinatarioTransacoes = destinatarioTransacoes;
        this.remetenteTransacoes = remetenteTransacoes;
        this.descricaoTransacoes = descricaoTransacoes;
    }

    public Integer getIdTransacao() {
        return idTransacao;
    }

    public String getDataTransacoes() {
        return dataTransacoes.toString();  // Retorna a data como string
    }

    public Double getValorTransacoes() {
        return valorTransacoes;
    }

    public String getDestinatarioTransacoes() {
        return destinatarioTransacoes;
    }

    public String getRemetenteTransacoes() {
        return remetenteTransacoes;
    }

    public String getDescricaoTransacoes() {
        return descricaoTransacoes;
    }
}
