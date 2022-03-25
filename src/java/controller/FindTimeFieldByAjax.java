/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.FieldDao;
import java.io.IOException;
import java.io.PrintWriter;
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
import model.PriceTime;
import model.TimeField;


/**
 *
 * @author Vang Nguyen
 */
@WebServlet(name = "FindTimeFieldByAjax", urlPatterns = {"/findTimeFieldByAjax"})
public class FindTimeFieldByAjax extends HttpServlet {

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
        String idF = request.getParameter("idF");
        String day = request.getParameter("day");
        
        FieldDao fieldDao = new FieldDao();
        
        List<TimeField> listTF = new ArrayList<>();
        
        if (fieldDao.getTimeFieldByIdFiledAndDay(idF, day).isEmpty()) {
            List<PriceTime> listPT = new ArrayList<>();
            listPT = fieldDao.getPriceTimeFieldByIdFiled(idF);
            for (PriceTime priceTime : listPT) {
                int start = Integer.parseInt( priceTime.getTimeStart().substring(0, 2));
                int end =  Integer.parseInt( priceTime.getTimeEnd().substring(0, 2));
                for (int i = start; i < end; i++) {
                    fieldDao.addTimeFieldByDay(idF, day,i+":00:00" , (i+1)+":00:00");                   
                }
            }
            listTF = fieldDao.getEmptyTimeFieldByIdFiledAndDay(idF, day);
        }  else{
            listTF = fieldDao.getEmptyTimeFieldByIdFiledAndDay(idF, day);
        }  
        PrintWriter out = response.getWriter();
        for (TimeField tf : listTF){
            out.println("<a href=\"confirmBookingInfo?idTF="+tf.getId()+"\" class=\"tag-cloud-link\">"+tf.getTimeStart()+"-"+tf.getTimeEnd()+"</a>");
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
            Logger.getLogger(FindTimeFieldByAjax.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(FindTimeFieldByAjax.class.getName()).log(Level.SEVERE, null, ex);
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
