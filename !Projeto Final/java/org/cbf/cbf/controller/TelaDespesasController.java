package org.cbf.cbf.controller;

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
import org.cbf.cbf.repository.Conexao;
import org.cbf.cbf.model.Despesas;

import java.io.IOException;
import java.sql.*;
import java.text.NumberFormat;
import java.util.Locale;

public class TelaDespesasController {

    @FXML private TextField txtPesquisar;

    @FXML private Label lblTotal;

    @FXML private Button btnNova, btnEditar, btnRemover, btnDespesa;

    @FXML private TableView<Despesas> tableViewDespesas;

    @FXML private TableColumn<Despesas, Integer> colId;

    @FXML private TableColumn<Despesas, Date> colData;

    @FXML private TableColumn<Despesas, Float> colValor;

    @FXML private TableColumn<Despesas, String> colDestinatario;

    @FXML private TableColumn<Despesas, String> colRemetente;

    @FXML private TableColumn<Despesas, String> colDescricao;

    private ObservableList<Despesas> listaDespesas;

    public void initialize() {
        txtPesquisar.textProperty().addListener((obs, oldValue, newValue) -> pesquisarDespesas(newValue));

        colId.setCellValueFactory(new PropertyValueFactory<>("idDespesa"));
        colData.setCellValueFactory(new PropertyValueFactory<>("dataDespesas"));
        colValor.setCellValueFactory(new PropertyValueFactory<>("valorDespesas"));
        colDestinatario.setCellValueFactory(new PropertyValueFactory<>("destinatarioDespesas"));
        colRemetente.setCellValueFactory(new PropertyValueFactory<>("remetenteDespesas"));
        colDescricao.setCellValueFactory(new PropertyValueFactory<>("descricaoDespesas"));

        formatarColunaValor();
        loadDataFromDatabase();
        atualizarTotal();

        tableViewDespesas.getItems().addListener((ListChangeListener<Despesas>) change -> atualizarTotal());
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
    private void irParaDividas(ActionEvent event) {
        trocarCena("/fxml/TelaDividas.fxml", event);
    }

    private void atualizarTotal() {
        double total = tableViewDespesas.getItems().stream()
                .mapToDouble(Despesas::getValorDespesas)
                .sum();

        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        lblTotal.setText("Total de despesas: " + formatter.format(total));
    }

    private void formatarColunaValor() {
        colValor.setCellFactory(col -> new TableCell<Despesas, Float>() {
            @Override
            protected void updateItem(Float item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(item));
                }
            }
        });
    }


    private void loadDataFromDatabase() {
        listaDespesas = FXCollections.observableArrayList();
        String sql = "SELECT * FROM despesas";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            // Verificando se o ResultSet contém dados
            if (!rs.isBeforeFirst()) {
                System.out.println("Nenhuma despesa encontrada no banco.");
            }

            while (rs.next()) {
                Despesas d = new Despesas(
                        rs.getInt("idDespesa"),
                        rs.getDate("dataDespesas"),
                        rs.getFloat("valorDespesas"),
                        rs.getString("destinatarioDespesas"),
                        rs.getString("remetenteDespesas"),
                        rs.getString("descricaoDespesas")
                );
                listaDespesas.add(d);
            }

            tableViewDespesas.setItems(listaDespesas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void pesquisarDespesas(String filtro) {
        ObservableList<Despesas> resultados = FXCollections.observableArrayList();
        String sql = "SELECT * FROM despesas WHERE " +
                "CAST(idDespesa AS CHAR) LIKE ? OR " +
                "CAST(dataDespesas AS CHAR) LIKE ? OR " +
                "CAST(valorDespesas AS CHAR) LIKE ? OR " +
                "destinatarioDespesas LIKE ? OR " +
                "remetenteDespesas LIKE ? OR " +
                "descricaoDespesas LIKE ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Atribuindo o filtro à consulta
            for (int i = 1; i <= 6; i++) {
                stmt.setString(i, "%" + filtro + "%");
            }

            ResultSet rs = stmt.executeQuery();

            // Verificando se a pesquisa retornou resultados
            if (!rs.isBeforeFirst()) {
                System.out.println("Nenhuma despesa encontrada para o filtro: " + filtro);
            }

            while (rs.next()) {
                Despesas d = new Despesas(
                        rs.getInt("idDespesa"),
                        rs.getDate("dataDespesas"),
                        rs.getFloat("valorDespesas"),
                        rs.getString("destinatarioDespesas"),
                        rs.getString("remetenteDespesas"),
                        rs.getString("descricaoDespesas")
                );
                resultados.add(d);
            }

            tableViewDespesas.setItems(resultados);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
