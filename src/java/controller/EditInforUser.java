/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.EditProfile;
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
import javax.servlet.http.HttpSession;
import model.User;
import util.ValidationInput;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "EditInforUser", urlPatterns = {"/edituser"})
public class EditInforUser extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            String fullname = request.getParameter("txtFullName");
            String email = request.getParameter("txtEmail");
            String phone = request.getParameter("txtPhone");
            String image = request.getParameter("txtImage");
            HttpSession s = request.getSession();
            User user = (User) s.getAttribute("ACCOUNT");
            String id = user.getId()+"";
            boolean error = false;
            String message = "";
            if (!ValidationInput.checkInputEmail(email, id,1)) {
                error = true;
                message = message + "Email";
            }
            if (!ValidationInput.checkInputPhone(phone, id,1)) {
                error = true;
                if (message == "") {
                    message = message + "Số điện thoại";
                } else {
                    message = message + " và Số điện thoại";
                }
            }
            if (error) {
                request.setAttribute("MESSAGE", message+" đã tồn tại hoặc không chính xác!");
            } else {
                System.out.println("asfasfasfsfasfas"+image);
                if (image.equalsIgnoreCase("")) {
                    image = user.getImage();
                }
                EditProfile edit = new EditProfile();
                edit.editInfor(id, fullname, image, email, phone);
                user = edit.showInfor(id);             
                s.setAttribute("ACCOUNT", user);
            }
            request.getRequestDispatcher("profile.jsp").forward(request, response);
            
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
            Logger.getLogger(EditInforUser.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(EditInforUser.class.getName()).log(Level.SEVERE, null, ex);
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
