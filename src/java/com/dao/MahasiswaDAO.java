/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import com.jdbc.db.MyConnection;
import com.model.Mahasiswa;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class MahasiswaDAO {

    private String INSERT_SQL = "INSERT INTO mahasiswa(nim, nama, semester, ipk) VALUES(?, ?, ?, ?)";
    private String DELETE_SQL = "DELETE FROM mahasiswa WHERE id = ?";
    private String UPDATE_SQL = "UPDATE mahasiswa SET nim = ?, nama = ?, semester = ?, ipk = ? WHERE id = ?";
    private String SELECT_SQL = "SELECT * FROM mahasiswa WHERE id = ?";
    private String SELECT_ALL_SQL = "SELECT * FROM mahasiswa";

    public List<Mahasiswa> selectAllMahasiswa() {
        List<Mahasiswa> listMahasiswa = new ArrayList<>();
        try {
            Connection conn = MyConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SELECT_ALL_SQL);

            Mahasiswa mhs = null;
            while (rs.next()) {
                int id = rs.getInt("id");
                String nim = rs.getString("nim");
                String nama = rs.getString("nama");
                int semester = rs.getInt("semester");
                float ipk = rs.getFloat("ipk");
                mhs = new Mahasiswa(id, nim, nama, semester, ipk);
                listMahasiswa.add(mhs);
            }

        } catch (SQLException e) {
            printSQLException(e);
        }
        return listMahasiswa;
    }

    public void insertMahasiswa(Mahasiswa mhs) {
        try {
            Connection conn = MyConnection.getConnection();
            PreparedStatement prepStmt = conn.prepareStatement(INSERT_SQL);
            prepStmt.setString(1, mhs.getNim());
            prepStmt.setString(2, mhs.getNama());
            prepStmt.setInt(3, mhs.getSemester());
            prepStmt.setFloat(4, mhs.getIpk());

            prepStmt.execute();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public void deleteMahasiswa(int id) {
        try {
            Connection conn = MyConnection.getConnection();
            PreparedStatement prepStmt = conn.prepareStatement(DELETE_SQL);
            prepStmt.setInt(1, id);
            int rowDeleted = prepStmt.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public void updateMahasiswa(int id, Mahasiswa mhs) {
        try {
            Connection conn = MyConnection.getConnection();
            PreparedStatement prepStmt = conn.prepareStatement(UPDATE_SQL);
            prepStmt.setString(1, mhs.getNim());
            prepStmt.setString(2, mhs.getNama());
            prepStmt.setInt(3, mhs.getSemester());
            prepStmt.setFloat(4, mhs.getIpk());
            prepStmt.setInt(5, id);

            int rowUpdated = prepStmt.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Mahasiswa selectMahasiswa(int id) {
        Mahasiswa mhs = null;
        try {
            Connection conn = MyConnection.getConnection();
            PreparedStatement prepStmt = conn.prepareStatement(SELECT_SQL);
            prepStmt.setInt(1, id);
            ResultSet rs = prepStmt.executeQuery();

            while (rs.next()) {
                String nim = rs.getString("nim");
                String nama = rs.getString("nama");
                int semester = rs.getInt("semester");
                float ipk = rs.getFloat("ipk");
                mhs = new Mahasiswa(id, nim, nama, semester, ipk);
                System.out.println("Nama : "+nama);

            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return mhs;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
