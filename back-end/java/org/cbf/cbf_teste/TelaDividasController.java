package org.cbf.cbf_teste;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.*;

public class TelaDividasController {
    @FXML
    TextField txtPesquisar;

    @FXML
    Button btnBuscar;

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
        this.colId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        this.colValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        this.colValidade.setCellValueFactory(new PropertyValueFactory<>("validade"));
        this.colJuros.setCellValueFactory(new PropertyValueFactory<>("juros"));
        this.colCredor.setCellValueFactory(new PropertyValueFactory<>("credor"));
        this.colFiador.setCellValueFactory(new PropertyValueFactory<>("fiador"));
        this.colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        this.colDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));

        loadDataFromDatabase();
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
}