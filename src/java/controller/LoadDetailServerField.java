/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.FieldDao;
import dao.ServerFieldDao;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Field;
import model.PriceTime;
import model.ServerField;
import model.TimeField;

/**
 *
 * @author Vang Nguyen
 */
@WebServlet(name = "LoadDetailServerField", urlPatterns = {"/LoadDetailServerField"})
public class LoadDetailServerField extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("idSF");
        ServerField serverField = new ServerFieldDao().getServerFieldById(id);
        if (id == null || serverField==null) {           
            response.sendRedirect("home");
        } else {
            try {
                List<Field> listF = new ArrayList<>();
                List<PriceTime> listPT = new ArrayList<>();
                List<TimeField> listTF = new ArrayList<>();                
                FieldDao fieldDao = new FieldDao();
                listF = fieldDao.getActivateFieldByIdServerFiled(id);              
                listPT = fieldDao.getPriceTimeFieldByIdServerFiled(id);                                                   
                request.setAttribute("listF", listF);
                request.setAttribute("listPT", listPT);                          
                request.setAttribute("sf", serverField);
                request.setAttribute("title", "Chi Tiết "+serverField.getNameServer());
                request.getRequestDispatcher("field-detail.jsp").forward(request, response);
            } catch (NullPointerException ex) {
                out.println("asd" + ex.getMessage());
            }
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(LoadDetailServerField.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(LoadDetailServerField.class.getName()).log(Level.SEVERE, null, ex);
        }
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
