package org.cbf.cbf.Controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.cbf.cbf.DB.Conexao;
import org.cbf.cbf.POJO.Receitas;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TelaReceitasController {
    public TextField txtPesquisarReceitas;
    public Button bttnCadastroReceitas;

    @FXML
    Button bttnCadastrar;

    @FXML
    private TableView<Receitas> receitasTableView;

    @FXML
    private TableColumn<Receitas, Integer> colIdReceitas;

    @FXML
    private TableColumn<Receitas, String> colDataReceitas;

    @FXML
    private TableColumn<Receitas, Double> colValorReceitas;

    @FXML
    private TableColumn<Receitas, String> colIrpjReceitas;

    @FXML
    private TableColumn<Receitas, String> colFonteReceitas;

    @FXML
    private TableColumn<Receitas, String> colDescricaoReceitas;

    @FXML
    private ObservableList<Receitas> listaReceitas;

    @FXML
    public void initialize() {
        txtPesquisarReceitas.textProperty().addListener((observable, oldValue, newValue) -> {
            pesquisarReceitas(newValue);
        });

        // Correct the property names to match the getters in Receitas class
        this.colIdReceitas.setCellValueFactory(new PropertyValueFactory<>("idReceita"));
        DateTimeFormatter formatoBR = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.colDataReceitas.setCellValueFactory(new PropertyValueFactory<>("dataReceita"));
        colDataReceitas.setCellFactory(column -> new TableCell<Receitas, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    try {
                        LocalDate data = LocalDate.parse(item); // Assume formato ISO vindo do banco (ex: 2025-04-15)
                        setText(data.format(formatoBR));
                    } catch (Exception e) {
                        setText(item); // Se não conseguir converter, exibe como veio
                    }
                }
            }
        });
        this.colValorReceitas.setCellValueFactory(new PropertyValueFactory<>("valorReceita"));
        this.colValorReceitas.setCellFactory(column -> {
            return new TableCell<Receitas, Double>() {
                @Override
                protected void updateItem(Double valor, boolean empty) {
                    super.updateItem(valor, empty);
                    if (empty || valor == null) {
                        setText(null);
                    } else {
                        setText(java.text.NumberFormat.getCurrencyInstance(new java.util.Locale("pt", "BR")).format(valor));
                    }
                }
            };
        });
        this.colFonteReceitas.setCellValueFactory(new PropertyValueFactory<>("fonteReceita"));
        this.colIrpjReceitas.setCellValueFactory(new PropertyValueFactory<>("irpjReceita"));
        this.colDescricaoReceitas.setCellValueFactory(new PropertyValueFactory<>("descricaoReceita"));

        loadFromDatabase();
    }


    private void loadFromDatabase() {
        this.listaReceitas = FXCollections.observableArrayList();
        String url="jdbc:mysql://localhost:3306/tarde_bd_cbf_teste";
        String user="root";
        String password=null;

        String query="select * from receitas";

        try (
                Connection conn= DriverManager.getConnection(url,user,password);
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();
        ){while (rs.next()){
            Receitas r=new Receitas(
                    rs.getInt("idReceita"),
                    rs.getString("dataReceitas"),
                    rs.getDouble("valorReceitas"),
                    rs.getString("fonte"),
                    rs.getDouble("IRPJ"),
                    rs.getString("descricaoReceitas")
            );
            this.listaReceitas.add(r);
        }
            this.receitasTableView.setItems(this.listaReceitas);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void pesquisarReceitas(String filtro) {
        ObservableList<Receitas> resultados = FXCollections.observableArrayList();

        String query="SELECT * FROM receitas WHERE " +
                "CAST(idReceita AS CHAR) LIKE ? OR " +
                "dataReceitas LIKE ? OR " +
                "valorReceitas LIKE ? OR " +
                "fonte LIKE ? OR " +
                "irpj LIKE ? OR " +
                "descricaoReceitas LIKE ?";

        try (Connection conn = Conexao.getConnection();  // Use the standard connection method
             PreparedStatement stmt = conn.prepareStatement(query)) {

            for (int i = 1; i <= 6; i++) {
                stmt.setString(i, "%" + filtro + "%");
            }

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Receitas r = new Receitas(
                        rs.getInt("idReceita"),
                        rs.getString("dataReceitas"),
                        rs.getDouble("valorReceitas"),
                        rs.getString("fonte"),
                        rs.getDouble("IRPJ"),
                        rs.getString("descricaoReceitas")
                );
                resultados.add(r);
            }
            this.receitasTableView.setItems(resultados);
        } catch (SQLException e) {
            e.printStackTrace();  // Consider handling with an error dialog in production
        }
    }
}
