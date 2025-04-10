package com.cbf1.cbf1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String url="jdbc:mysql://localhost:3306/tarde_bd_cbf_teste";
    private static final String user="root";
    private static final String password=null;

    public static Connection getConnection()throws SQLException{
        return DriverManager.getConnection(url,user,password);
    }
}