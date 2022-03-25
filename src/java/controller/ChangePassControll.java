/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.EditProfile;
import dao.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
 * @author PC
 */
@WebServlet(name = "ChangePassControll", urlPatterns = {"/changePass"})
public class ChangePassControll extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            request.setAttribute("title", "Đổi mật khẩu");
            request.getRequestDispatcher("changepass.jsp").forward(request, response);
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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String passOld = request.getParameter("txtPassOld");
            String passNew = request.getParameter("txtPassNew");
            String passNewAgain = request.getParameter("txtPassNewAgain");
            HttpSession s = request.getSession();
            User user = (User) s.getAttribute("ACCOUNT");
            int id = (user == null) ? 0 : user.getId();
            boolean error = false;
            String message = "";
            if (id == 0) {
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
            if (!ValidationInput.checkIsEmpty(passOld) || !ValidationInput.checkIsEmpty(passNew) || !ValidationInput.checkIsEmpty(passNewAgain)) {
                error = true;
                message = "Bắt buộc nhập các trường có dấu *.";
            } else if (!passNew.equals(passNewAgain)) {
                error = true;
                message = "Nhập lại mật khẩu không chính xác.";
            } else {
                try {
                    User nd = new UserDao().checkUser(id);
                    if (nd == null) {
                        error = true;
                        message = "Người dùng không tồn tại.";
                    } else if (!nd.getPassword().equals(passOld)) {
                        error = true;
                        message = "Mật khẩu cũ không chính xác.";
                    }
                } catch (SQLException e) {
                }
            }
            if (error) {
                request.setAttribute("MESSAGE", message);
                request.getRequestDispatcher("changepass.jsp").forward(request, response);
            } else {
                System.out.println("thanh cong");
                try {
                    if (new UserDao().updatePassUser(id, passNew)) {
                        request.setAttribute("SUCCESS", "Thay đổi thành công");
                        request.getRequestDispatcher("changepass.jsp").forward(request, response);
                    }
                } catch (SQLException e1) {
                }
            }
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
