/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CommentDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Comment;

/**
 *
 * @author Vang Nguyen
 */
@WebServlet(name = "LoadReplyComment", urlPatterns = {"/load-reply-comment"})
public class LoadReplyComment extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
  

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
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("idC");
        List<Comment> listReplyC = new ArrayList<>();
        listReplyC = new CommentDAO().getReplyCommentByIdComment(id);
        try (PrintWriter out = response.getWriter()) {
            if (!listReplyC.isEmpty()) {
                for (Comment c : listReplyC) {
                    out.println("<li class=\"comment\">"
                            + "<div class=\"vcard bio\">\n"
                            + "                        <img src=\"" + (c.getImage() == null ? "images/avatar.png" : c.getImage()) + "\" style=\"\">\n"
                            + "                    </div>\n"
                            + "                    <div class=\"comment-body\">\n"
                            + "                        <h3>" + c.getUser() + "</h3>\n"
                            + "                        <p>" + c.getComment() + "</p>\n"                 
                            + "                    </div>"
                                    + "</li>");
                }
            }

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
