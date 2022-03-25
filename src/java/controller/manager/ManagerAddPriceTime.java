/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.manager;

import dao.FieldDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Vang Nguyen
 */
@WebServlet(name = "ManagerAddPriceTime", urlPatterns = {"/manager-add-time"})
public class ManagerAddPriceTime extends HttpServlet {

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
            throws ServletException, IOException, SQLException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        String idF = request.getParameter("idF");
        String timeStart = request.getParameter("timeStart");
        String timeEnd = request.getParameter("timeEnd");
        String price = request.getParameter("price");

        FieldDao fd = new FieldDao();
        try (PrintWriter out = response.getWriter()) {
            if (fd.checkPriceTimeConflit(idF, timeStart, timeEnd) || timeStart.equalsIgnoreCase(timeEnd) || Integer.parseInt(timeStart.substring(0, 2))>Integer.parseInt(timeEnd.substring(0, 2))) {
                out.println("<p class=\"text-center text-danger\"><strong>Khung giờ đã sung đột với các khung giờ khác</strong></p>");                
            } else{
                if(fd.addNewPriceTimeByIdF(idF, timeStart, timeEnd, price)){
                    out.print("done");
                } else{
                out.println("<p class=\"text-center text-danger\"><strong>Đã có lỗi sảy ra vui lòng thử lại</strong></p>");
                }
      
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
            Logger.getLogger(ManagerAddPriceTime.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ManagerAddPriceTime.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ManagerAddPriceTime.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ManagerAddPriceTime.class.getName()).log(Level.SEVERE, null, ex);
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
