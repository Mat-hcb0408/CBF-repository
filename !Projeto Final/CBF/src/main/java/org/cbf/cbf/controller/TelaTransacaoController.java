package org.cbf.cbf.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.cbf.cbf.repository.Conexao;
import org.cbf.cbf.model.Transacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class TelaTransacaoController {

    @FXML
    private TableView<Transacao> tabelaTransacoes;

    @FXML
    private TableColumn<Transacao, Integer> colTransacaoId;

    @FXML
    private TableColumn<Transacao, String> colTransacaoData;

    @FXML
    private TableColumn<Transacao, Double> colTransacaoValor;

    @FXML
    private TableColumn<Transacao, String> colTransacaoDestinatario;

    @FXML
    private TableColumn<Transacao, String> colTransacaoRemetente;

    @FXML
    private TableColumn<Transacao, String> colTransacaoDescricao;

    private ObservableList<Transacao> listaTransacoes;

    @FXML
    private TextField txtPesquisar;

    @FXML
    public void initialize() {

        txtPesquisar.textProperty().addListener((observable, oldValue, newValue) -> {
            pesquisarTransacoes(newValue);
        });

        colTransacaoId.setCellValueFactory(new PropertyValueFactory<>("idTransacao"));
        colTransacaoData.setCellValueFactory(new PropertyValueFactory<>("dataTransacoes"));
        colTransacaoValor.setCellValueFactory(new PropertyValueFactory<>("valorTransacoes"));
        colTransacaoDestinatario.setCellValueFactory(new PropertyValueFactory<>("destinatarioTransacoes"));
        colTransacaoRemetente.setCellValueFactory(new PropertyValueFactory<>("remetenteTransacoes"));
        colTransacaoDescricao.setCellValueFactory(new PropertyValueFactory<>("descricaoTransacoes"));

        atualizarValor();
        loadDataFromDatabase();
    }

    public void atualizarValor() {
        colTransacaoValor.setCellFactory(col -> new TableCell<Transacao, Double>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
                    setText(formatter.format(item));
                }
            }
        });

        colTransacaoData.setCellFactory(col -> new TableCell<Transacao, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    // Formatar a data
                    LocalDate date = LocalDate.parse(item);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    setText(formatter.format(date));
                }
            }
        });

    }


    private void loadDataFromDatabase() {
        listaTransacoes = FXCollections.observableArrayList();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexao.getConnection();
            String sql = "SELECT * FROM transacoes";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Transacao t = new Transacao(
                        rs.getInt("idTransacao"),
                        rs.getString("dataTransacoes"),
                        rs.getDouble("valorTransacoes"),
                        rs.getString("destinatarioTransacoes"),
                        rs.getString("remetenteTransacoes"),
                        rs.getString("descricaoTransacoes")
                );
                listaTransacoes.add(t);
            }

            tabelaTransacoes.setItems(listaTransacoes);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (Exception ignored) {
            }
            try {
                if (stmt != null) stmt.close();
            } catch (Exception ignored) {
            }
            try {
                if (conn != null) conn.close();
            } catch (Exception ignored) {
            }
        }
    }

    //PESQUISA
    private void pesquisarTransacoes(String filtro) {
        ObservableList<Transacao> resultados = FXCollections.observableArrayList();

        // A consulta SQL agora está corretamente estruturada
        String sql = "SELECT * FROM transacoes WHERE " +
                "CAST(idTransacao AS CHAR) LIKE ? OR " +
                "CAST(valorTransacoes AS CHAR) LIKE ? OR " +
                "dataTransacoes LIKE ? OR " +
                "destinatarioTransacoes LIKE ? OR " +
                "remetenteTransacoes LIKE ? OR " +
                "descricaoTransacoes LIKE ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Passando 6 parâmetros para o PreparedStatement
            for (int i = 1; i <= 6; i++) {
                stmt.setString(i, "%" + filtro + "%");
            }

            // Executa a consulta
            ResultSet rspesquisa = stmt.executeQuery();

            // Limpar os resultados anteriores
            resultados.clear();

            // Adiciona as transações que correspondem ao filtro
            while (rspesquisa.next()) {
                Transacao t = new Transacao(
                        rspesquisa.getInt("idTransacao"),
                        rspesquisa.getString("dataTransacoes"),
                        rspesquisa.getDouble("valorTransacoes"),
                        rspesquisa.getString("destinatarioTransacoes"),
                        rspesquisa.getString("remetenteTransacoes"),
                        rspesquisa.getString("descricaoTransacoes")
                );
                resultados.add(t);
            }

            // Atualiza a tabela com os novos resultados filtrados
            tabelaTransacoes.setItems(resultados);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

