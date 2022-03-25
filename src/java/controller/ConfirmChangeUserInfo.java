/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;
import util.ValidationInput;

/**
 *
 * @author Vang Nguyen
 */
@WebServlet(name = "ConfirmChangeUserInfo", urlPatterns = {"/confirmChangeUserInfo"})
public class ConfirmChangeUserInfo extends HttpServlet {

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
        String fullname = request.getParameter("fullname");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        try {
            PrintWriter out = response.getWriter();
            HttpSession s = request.getSession();
            User user = (User) s.getAttribute("ACCOUNT");
            boolean error = false;
            String message = "";
            if (!ValidationInput.checkInputEmail(email, ""+user.getId(),1)) {
                error = true;
                message = message + "Email";
            }
            if (!ValidationInput.checkInputPhone(phone , ""+user.getId(),1)) {
                error = true;
                if (message == "") {
                    message = message + "Số điện thoại";
                } else {
                    message = message + "và Số điện thoại";
                }
            }
            if (error) {
                out.printf("<p class=\"text-center text-danger\"><strong>%s đã tồn tại hoặc không chính xác!</strong></p>", message);
            } else{
                if (new UserDao().updateUserInfoConfirm(fullname, phone, email, "" + user.getId())) {
                    user = new UserDao().checkLogin(user.getUsername(), user.getPassword());
                    s.setAttribute("ACCOUNT", user);
                    out.println("<p class=\"text-center text-success\"><strong>Thay thay đổi thành công!</strong></p>");
                } else {
                    out.println("<p class=\"text-center text-danger\"><strong>Thay thay đổi không thành công!</strong></p>");
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(ConfirmBookingInfo.class.getName()).log(Level.SEVERE, null, ex);
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
