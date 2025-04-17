package org.cbf.cbf.Controller;


import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.cbf.cbf.DB.Conexao;
import org.cbf.cbf.POJO.Divida;

import java.io.IOException;
import java.sql.*;
import java.text.NumberFormat;
import java.util.Locale;

public class TelaDividasController {

    @FXML
    TextField txtPesquisar;

    @FXML
    Label lblTotal;

    @FXML
    Button btnNova;

    @FXML
    Button btnEditar;

    @FXML
    Button btnRemover;

    @FXML
    Button btnDespesa;

    @FXML
    private TableView<Divida> tableViewDividas;

    @FXML
    private TableColumn<Divida, Integer> colId;

    @FXML
    private TableColumn<Divida, Double> colValor;

    @FXML
    private TableColumn<Divida, String> colValidade;

    @FXML
    private TableColumn<Divida, Double> colJuros;

    @FXML
    private TableColumn<Divida, String> colCredor;

    @FXML
    private TableColumn<Divida, String> colFiador;

    @FXML
    private TableColumn<Divida, String> colTipo;

    @FXML
    private TableColumn<Divida, String> colDescricao;

    private ObservableList<Divida> listaDividas;

    public void initialize() {

        txtPesquisar.textProperty().addListener((observable, oldValue, newValue) -> {
            pesquisarDividas(newValue);
        });

        this.colId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        this.colValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        this.colValidade.setCellValueFactory(new PropertyValueFactory<>("validade"));
        this.colJuros.setCellValueFactory(new PropertyValueFactory<>("juros"));
        this.colCredor.setCellValueFactory(new PropertyValueFactory<>("credor"));
        this.colFiador.setCellValueFactory(new PropertyValueFactory<>("fiador"));
        this.colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        this.colDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));

        loadDataFromDatabase();
        atualizarValor();
        atualizarTotal();
        tableViewDividas.getItems().addListener((ListChangeListener<Divida>) change -> atualizarTotal());
    }

    private void trocarCena(String caminhoFXML, ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(caminhoFXML));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void irParaDespesas(ActionEvent event) {
        trocarCena("/org/cbf/cbf/TelaDespesas.fxml", event);
    }

    private void atualizarTotal(){
        double total = 0.0;

        for (Divida divida : tableViewDividas.getItems()){
            double valor = divida.getValor();
            double juros = divida.getJuros();
            total += valor + juros;
        }

        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        lblTotal.setText("Total em dívidas: " + formatter.format(total));
    }

    public void atualizarValor(){
        colValor.setCellFactory(col -> new TableCell<Divida, Double>() {
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

        colJuros.setCellFactory(col -> new TableCell<Divida, Double>() {
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
        this.listaDividas = FXCollections.observableArrayList();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexao.getConnection(); // método que retorna a conexão
            String sql = "SELECT * FROM dividas"; // ajuste conforme sua tabela
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Divida d = new Divida(
                        rs.getInt("idDivida"),
                        rs.getDouble("valorDividas"),
                        rs.getString("validade"),
                        rs.getDouble("juros"),
                        rs.getString("credor"),
                        rs.getString("fiador"),
                        rs.getString("tipoDeDivida"),
                        rs.getString("descricaoDividas")
                );
                this.listaDividas.add(d);
            }

            this.tableViewDividas.setItems(this.listaDividas);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception ignored) {}
            try { if (stmt != null) stmt.close(); } catch (Exception ignored) {}
            try { if (conn != null) conn.close(); } catch (Exception ignored) {}
        }
    }

    //PESQUISA
    private void pesquisarDividas(String filtro){
        ObservableList<Divida> resultados = FXCollections.observableArrayList();

        String sql = "SELECT * FROM dividas WHERE " +
                "CAST(idDivida AS CHAR) LIKE ? OR " +
                "CAST(valorDividas AS CHAR) LIKE ? OR " +
                "validade LIKE ? OR " +
                "CAST(juros AS CHAR) LIKE ? OR " +
                "credor LIKE ? OR " +
                "fiador LIKE ? OR " +
                "tipoDeDivida LIKE ? OR " +
                "descricaoDividas LIKE ?";

        try (Connection conn = Conexao.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (int i = 1; i<=8;i++){
                stmt.setString(i, "%" + filtro + "%");
            }

            ResultSet rspesquisa = stmt.executeQuery();

            while(rspesquisa.next()) {
                Divida d = new Divida(
                        rspesquisa.getInt("idDivida"),
                        rspesquisa.getDouble("valorDividas"),
                        rspesquisa.getString("validade"),
                        rspesquisa.getDouble("juros"),
                        rspesquisa.getString("credor"),
                        rspesquisa.getString("fiador"),
                        rspesquisa.getString("tipoDeDivida"),
                        rspesquisa.getString("descricaoDividas")
                );
                resultados.add(d);
            }
            tableViewDividas.setItems(resultados);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
