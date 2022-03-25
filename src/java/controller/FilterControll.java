/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CountyDao;
import dao.ServerFieldDao;
import java.io.IOException;

import static java.lang.System.out;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.County;
import model.ServerField;

/**
 *
 * @author Vang Nguyen
 */
@WebServlet(name = "FilterControll", urlPatterns = {"/filter-controll"})
public class FilterControll extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name") == null ? "" : request.getParameter("name");
        int county = request.getParameter("county") == null ? 0 : Integer.valueOf(request.getParameter("county"));
        try{
            List<ServerField> listSF = new ArrayList<>();
            List<County> listCT = new ArrayList<>();
            listSF = new ServerFieldDao().searchServerField(name.toLowerCase(), county);
            listCT = new CountyDao().getAllCounty();

            request.setAttribute("listSF", listSF);
            request.setAttribute("listCT", listCT);
            request.setAttribute("name", name);
            request.setAttribute("county", county);
            request.setAttribute("title", "Tìm Kiếm Sân");
            request.getRequestDispatcher("filter.jsp").forward(request, response);
        }catch(Exception ex){
            out.println("asd"+ ex.getMessage() );
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
        processRequest(request, response);
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
        processRequest(request, response);
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
