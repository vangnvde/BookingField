/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CommentDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author Vang Nguyen
 */
@WebServlet(name = "AddComment", urlPatterns = {"/add-comment"})
public class AddComment extends HttpServlet {

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
        String idC = request.getParameter("id");
        String idSF = request.getParameter("idSF");
        String content = request.getParameter("cmt");
        HttpSession s = request.getSession();
        User user = (User) s.getAttribute("ACCOUNT");

        try (PrintWriter out = response.getWriter()) {
            if (idC.equalsIgnoreCase("0")) {
                
                if (new CommentDAO().addNewComment(user.getId() + "", idSF, content)) {
                    out.print("done");         
                } else {
                    out.print("<p class=\"text-center text-danger\"><strong>Đã có lỗi sảy ra vui lòng thử lại</strong></p>");
                }
            } else {
                if (new CommentDAO().addReplyComment(user.getId() + "", idSF, content, idC)) {
                    out.print("done");
                } else {
                    out.print("<p class=\"text-center text-danger\"><strong>Đã có lỗi sảy ra vui lòng thử lại</strong></p>");
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
