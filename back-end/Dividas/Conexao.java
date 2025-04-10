package org.cbf.cbf_teste;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String URL = "jdbc:mysql://localhost:3306/tarde_bd_cbf_teste";
    private static final String USER = "root";
    private static final String PASSWORD = null; // ou "" se for senha vazia

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}