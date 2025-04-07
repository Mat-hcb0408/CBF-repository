package com.cbf.cbf;

import java.sql.*;

public class query {
    public static void main(String[] args) throws SQLException {
// Defina as informações de conexão
        String url = "jdbc:mysql: //localhost:3306/tarde_bd_CBF "; // URL de conexão com o MySQL
        String user = "root"; // Nome do usuário
        String password = null; // Senha do banco de dados
// Conectar ao banco de dados
        try (Connection conexao = DriverManager.getConnection(url, user, null)) {
            System.out.println("Conexão realizada com sucesso !");

// Criar uma consulta simples
            String query = "SELECT * FROM Clubes"; // Modifique com o nome da tabela do seu banco
            try (Statement stmt = conexao.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {
// Exibir os resultados
                while (rs.next()) {
                    System.out.println(rs.getString("nome_clube"));
                    System.out.println(rs.getString("anoFundacao"));
                    System.out.println(rs.getString("estado"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}