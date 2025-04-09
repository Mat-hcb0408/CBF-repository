package org.example.projeto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class DividasController implements Initializable {

    @FXML private TableView<Divida> tableViewDividas;
    @FXML private TextField txtPesquisar;
    @FXML private Label lblTotal;
    @FXML private Button btnNova, btnEditar, btnRemover, btnBuscar;

    private ObservableList<Divida> listaDividas = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarTabela();
        tableViewDividas.setItems(listaDividas);
        atualizarTotal();
    }

    private void configurarTabela() {
        // Importante: certifique-se que as colunas estejam nessa ordem no FXML
        TableColumn<Divida, Integer> colId = (TableColumn<Divida, Integer>) tableViewDividas.getColumns().get(0);
        TableColumn<Divida, Double> colValor = (TableColumn<Divida, Double>) tableViewDividas.getColumns().get(1);
        TableColumn<Divida, String> colValidade = (TableColumn<Divida, String>) tableViewDividas.getColumns().get(2);
        TableColumn<Divida, Double> colJuros = (TableColumn<Divida, Double>) tableViewDividas.getColumns().get(3);
        TableColumn<Divida, String> colCredor = (TableColumn<Divida, String>) tableViewDividas.getColumns().get(4);
        TableColumn<Divida, String> colTipo = (TableColumn<Divida, String>) tableViewDividas.getColumns().get(5);
        TableColumn<Divida, String> colDescricao = (TableColumn<Divida, String>) tableViewDividas.getColumns().get(6);

        colId.setCellValueFactory(data -> data.getValue().idProperty().asObject());
        colValor.setCellValueFactory(data -> data.getValue().valorProperty().asObject());
        colValidade.setCellValueFactory(data -> data.getValue().validadeProperty());
        colJuros.setCellValueFactory(data -> data.getValue().jurosProperty().asObject());
        colCredor.setCellValueFactory(data -> data.getValue().credorProperty());
        colTipo.setCellValueFactory(data -> data.getValue().tipoProperty());
        colDescricao.setCellValueFactory(data -> data.getValue().descricaoProperty());
    }

    @FXML
    private void novaDivida() {
        int novoId = listaDividas.size() + 1;
        Divida nova = new Divida(novoId, 250.00, "2025-12-31", 3.5, "Banco Central", "Cartão", "Dívida de teste");
        listaDividas.add(nova);
        atualizarTotal();
    }

    @FXML
    private void editarDivida() {
        Divida selecionada = tableViewDividas.getSelectionModel().getSelectedItem();
        if (selecionada != null) {
            selecionada.setDescricao(selecionada.getDescricao() + " (editado)");
            tableViewDividas.refresh();
        }
    }

    @FXML
    private void removerDivida() {
        Divida selecionada = tableViewDividas.getSelectionModel().getSelectedItem();
        if (selecionada != null) {
            listaDividas.remove(selecionada);
            atualizarTotal();
        }
    }

    @FXML
    private void buscarDividas() {
        String termo = txtPesquisar.getText().toLowerCase();
        tableViewDividas.setItems(listaDividas.filtered(d -> d.getDescricao().toLowerCase().contains(termo)));
    }

    private void atualizarTotal() {
        double total = listaDividas.stream().mapToDouble(Divida::getValor).sum();
        lblTotal.setText(String.format("Total em dívidas: R$%.2f", total));
    }
}
