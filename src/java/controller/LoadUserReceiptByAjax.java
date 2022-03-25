/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ReceiptDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Receipt;
import model.User;

/**
 *
 * @author Vang Nguyen
 */
@WebServlet(name = "LoadUserReceiptByAjax", urlPatterns = {"/loadUserReceiptByAjax"})
public class LoadUserReceiptByAjax extends HttpServlet {

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
        List<Receipt> listR = new ArrayList<>();
        HttpSession s = request.getSession();
        User user = (User) s.getAttribute("ACCOUNT");
        listR = new ReceiptDao().getLastFourReceiptByUserID("" + user.getId());
        HashMap<String, String> status = new HashMap<String, String>(); 
        status.put("Đang chờ", "<center><a class=\"btn-primary\" style=\"font-size:70%;width:50%;color:white;\">Đang Chờ</a></center>");
        status.put("Đã Xác Nhận", "<center><a class=\" btn-success\" style=\"font-size:70%;width:50%;color:white;\">Đã Xác Nhận</a></center>");
        status.put("Đã Hủy", "<center><a class=\"btn-danger\" style=\"font-size:70%;width:50%;color:white;\">Đã Hủy</a></center>");
        PrintWriter out = response.getWriter();
        for (Receipt r : listR) {
            out.println("<div class=\"notification-item\" >\n"
                    + "<a href=\"#\"> "+r.getNameServerField() +"</a>\n"
                    + "<a style=\"font-size:80%\"><span class=\"icon-calendar\"></span> " + r.getDate() + ", " + r.getTimeStart() + "-" + r.getTimeEnd() + "</a>\n"
                    + "<a style=\"font-size:80%\"><img style=\"width:13px\" src=\"images/people.png\"> " + r.getNameField() + "</a>\n"
                    + "<a style=\"font-size:80%\"><img style=\"width:13px\" src=\"images/prices.png\"> " + r.getPrice() + " VND" + "</a>\n"
                    + status.get(r.getStatus())
                    + "</div>\n");
        }
        out.println("<div class=\"notification-item\" >\n"
                    +"<a href=\"showInfor\" >Xem Toàn Bộ</a>\n" 
                + "</div>\n");
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
            Logger.getLogger(LoadUserReceiptByAjax.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(LoadUserReceiptByAjax.class.getName()).log(Level.SEVERE, null, ex);
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
