/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.servlet;

import com.dao.MahasiswaDAO;
import com.model.Mahasiswa;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.sql.*;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "MahasiswaServlet", urlPatterns = {"/"})
public class MahasiswaServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private MahasiswaDAO mahasiswaDAO;

    public void init() {
        mahasiswaDAO = new MahasiswaDAO();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        String action = request.getServletPath();
        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertMahasiswa(request, response);
                    break;
                case "/delete":
                    deleteMahasiswa(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateMahasiswa(request, response);
                    break;
                default:
                    listMahasiswa(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listMahasiswa(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List< Mahasiswa> listMahasiswa = mahasiswaDAO.selectAllMahasiswa();
        request.setAttribute("listMahasiswa", listMahasiswa);
        RequestDispatcher dispatcher = request.getRequestDispatcher("mahasiswa-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("mahasiswa-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Mahasiswa existingMahasiswa = mahasiswaDAO.selectMahasiswa(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("mahasiswa-form.jsp");
        request.setAttribute("mahasiswa", existingMahasiswa);
        dispatcher.forward(request, response);

    }

    private void insertMahasiswa(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String nim = request.getParameter("nim");
        String nama = request.getParameter("nama");
        int semester = Integer.parseInt(request.getParameter("semester"));
        float ipk = Float.parseFloat(request.getParameter("ipk"));
        Mahasiswa newMahasiswa = new Mahasiswa(nim, nama, semester, ipk);
        mahasiswaDAO.insertMahasiswa(newMahasiswa);
        response.sendRedirect("list");
    }

    private void updateMahasiswa(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nim = request.getParameter("nim");
        String nama = request.getParameter("nama");
        int semester = Integer.parseInt(request.getParameter("semester"));
        float ipk = Float.parseFloat(request.getParameter("ipk"));

        Mahasiswa mhs = new Mahasiswa(id, nim, nama, semester, ipk);
        mahasiswaDAO.updateMahasiswa(id, mhs);
        response.sendRedirect("list");
    }

    private void deleteMahasiswa(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        mahasiswaDAO.deleteMahasiswa(id);
        response.sendRedirect("list");

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
