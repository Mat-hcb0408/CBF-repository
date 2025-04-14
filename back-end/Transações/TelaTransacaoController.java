package org.cbf.cbf_teste;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
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

    public void Initialize(){
        this.colTransacaoId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        this.colTransacaoData.setCellValueFactory(new PropertyValueFactory<>("valor"));
        this.colTransacaoValor.setCellValueFactory(new PropertyValueFactory<>("validade"));
        this.colTransacaoDestinatario.setCellValueFactory(new PropertyValueFactory<>("juros"));
        this.colTransacaoRemetente.setCellValueFactory(new PropertyValueFactory<>("credor"));
        this.colTransacaoDescricao.setCellValueFactory(new PropertyValueFactory<>("fiador"));

    }

    public void atualizarValor(){
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
    }

    private void loadDataFromDatabase() {
        this.listaTransacoes = FXCollections.observableArrayList();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexao.getConnection(); // método que retorna a conexão
            String sql = "SELECT * FROM dividas"; // ajuste conforme sua tabela
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Transacao t = new Transacao(
                        rs.getInt("idDivida"),
                        rs.getDouble("valorDividas"),
                        rs.getString("validade"),
                        rs.getDouble("juros"),
                        rs.getString("credor"),
                        rs.getString("fiador"),
                        rs.getString("tipoDeDivida"),
                        rs.getString("descricaoDividas")
                );
                this.listaTransacoes.add(t);
            }

            this.listaTransacoes.setItems(this.listaTransacoes);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception ignored) {}
            try { if (stmt != null) stmt.close(); } catch (Exception ignored) {}
            try { if (conn != null) conn.close(); } catch (Exception ignored) {}
        }
    }

}
