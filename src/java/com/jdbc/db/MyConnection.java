/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jdbc.db;

import java.sql.*;

/**
 *
 * @author Administrator
 */
public class MyConnection {

    private static Connection conn;
    private static String url = "jdbc:mysql://localhost:3306/universitas";
    private static String user = "root";
    private static String password = "";

    public MyConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.getLogger(MyConnection.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    public static Connection getConnection() {
        if (conn == null) {
            try {
                 Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(url, user, password);
            } catch (Exception ex) {

            }
        }
        return conn;

    }
}
